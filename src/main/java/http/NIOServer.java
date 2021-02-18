package http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadPool.HttpThreadPool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @Auther: shenyafeng
 * @Date: 2021/2/10 10:04
 * @Description:
 */


public class NIOServer implements Runnable {
    private static Logger myLogger = LoggerFactory.getLogger(NIOServer.class);
    //可读可写
    private ServerSocketChannel acceptChannel;
    //多路复用器
    private Selector selector;

    private volatile boolean stop;
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
    private static final String BLANK = " ";
    private static final String CRLF = "\r\n";

    public NIOServer(int port) {
        try {
            selector = Selector.open();
            acceptChannel = ServerSocketChannel.open();
            acceptChannel.configureBlocking(false);
            acceptChannel.socket().bind(new InetSocketAddress(port), 1024);
            acceptChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            myLogger.error("创建nio服务器出错");
            System.exit(1);
        }

    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(100000);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            SelectionKey key = null;
            while (iterator.hasNext()) {
                key = iterator.next();
                iterator.remove();
                HttpThreadPool.executor.execute(new HttpTask(key, selector));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(NIOServer.class);
        int port = 8080;
        NIOServer server = new NIOServer(port);
        new Thread(server, "NIOTHREAD").start();
        myLogger.info("服务器已启动");
    }
}
