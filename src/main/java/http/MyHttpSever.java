package http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sun.misc.PostVMInitHook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @Auther: shenyafeng
 * @Date: 2021/2/4 21:37
 * @Description:http服务器
 */

public class MyHttpSever implements Runnable{
    private final Logger logger=LoggerFactory.getLogger(MyHttpSever.class);
    private ServerSocket serverSocket;
    private int port=8080;
    public MyHttpSever(){
        try {
            serverSocket=new ServerSocket(port);
        } catch (IOException e) {
            logger.error("启动服务器失败",e);
        }
        if(serverSocket==null){
            logger.error("启动服务器失败,socket为null");
            System.exit(1);
        }
        new Thread(this).start();
        logger.info("服务器成功启动，端口号为"+port);
    }
    @Override
    public void run() {
        System.out.println("准备启动服务器");
        while (true) {
            try {
                Socket client = serverSocket.accept();
                logger.info("连接的客户端为"+client.toString());
                InputStreamReader inputStream = new InputStreamReader(client.getInputStream());
                BufferedReader reader=new BufferedReader(inputStream);
                String s = "";
                while (!StringUtils.isEmpty((s=reader.readLine()))){
                    System.out.println(s);
                }
            } catch (IOException e) {
                logger.error("接收客户端连接出错",e);
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {

        MyHttpSever sever=new MyHttpSever();
        while (true){
            Thread.sleep(100000);
            System.out.println("再次睡眠");
        }
    }
}
