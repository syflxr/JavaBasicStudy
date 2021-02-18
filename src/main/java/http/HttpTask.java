package http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * @Auther: shenyafeng
 * @Date: 2021/2/11 14:13
 * @Description:有新的事件时扔给线程池处理
 */

public class HttpTask implements Runnable{
    private static final Logger myLogger= LoggerFactory.getLogger(HttpTask.class);
    //构造html的前面的部分
    private static final String HTML_HEADER = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "</head>\n" +
            "\n" +
            "<body>";
    //构造html后面的部分
    private static final String HTML_TAIL = "</body>\n" +
            "</html>";
    private static final String BLANK=" ";
    private static final String CRLF="\r\n";

    private Selector selector;
    private SelectionKey key;

    public HttpTask(SelectionKey key,Selector selector){
        this.selector=selector;
        this.key=key;
    }
    @Override
    public void run() {
        try {
            handleNewEvent(key);
        } catch (Exception e) {
            myLogger.error("处理新事件出错",e);
            if (key != null) {
                key.cancel();
                try {
                    key.channel().close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

    }

    /**
     * @description:处理新的事件
     * @param key
     * @return: void
     * @author shenyafeng
     * @date: 2021/2/11 14:19
     */
    private void handleNewEvent(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                System.out.println("有新连接到来");
                //建立新链接
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel accept = channel.accept();
                if(accept==null){
                    return;
                }
                accept.configureBlocking(false);
                accept.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                System.out.println("发生可读事件");
                //可读
                SocketChannel channel = (SocketChannel) key.channel();
                ByteBuffer buff = ByteBuffer.allocate(1024);
                int count = channel.read(buff);
                if (count > 0) {
                    //指针头移动到缓冲区最前面，并且最多能读上次写入的数据量
                    buff.flip();
                    byte[] bytes = new byte[buff.remaining()];
                    buff.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    MyHttpRequestContext myHttpRequestContext=new MyHttpRequestContext(body);
                    String url = myHttpRequestContext.getParam("url");
                    if(url.equals("/login")||url.equals("/")){
                        String responseByUrl = HttpResponseContent.getResponseByUrl(url);
                        if(!StringUtils.isEmpty(responseByUrl)){
                            doWrite(channel, responseByUrl);
                        }
                    }
                    else {
                        doWrite(channel,HTML_HEADER+"fa"+HTML_TAIL);
                    }
                } else {
                    key.cancel();
                    channel.close();
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, java.lang.String res) throws IOException {
        System.out.println("准备写入");
        StringBuilder response=new StringBuilder("");
        //1)  HTTP协议版本、状态代码、描述
        response.append("HTTP/1.1").append(BLANK).append("200").append(BLANK).append("OK").append(CRLF);
        //2)  响应头(Response Head)
        response.append("Server:syf Server").append(CRLF);
        response.append("Date:").append(new Date()).append(CRLF);
        response.append("Content-type:text/html;charset=UTF-8").append(CRLF);
        //正文长度 ：字节长度
        response.append("Content-Length:").append(res.toString().getBytes().length).append(CRLF);
        //3)正文之前
        response.append(CRLF);
        //4)正文
        response.append(res);

        byte[] bytes = response.toString().getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        channel.write(buffer);
        System.out.println("写入结束");
    }
}
