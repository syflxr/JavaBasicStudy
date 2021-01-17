package net;

import com.mysql.jdbc.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Auther: shenyafeng
 * @Date: 2020/12/17 21:38
 * @Description:
 */

public class BioTest {
    public void BioServerTest() throws IOException {
        ServerSocket socket = new ServerSocket(8080);//不传IP默认为本机ip
        Socket clientSocket = null;
        while (true) {
            clientSocket = socket.accept();
            new Thread(new SocketHandler(clientSocket)).start();
        }


    }
}

class SocketHandler implements Runnable {
    private Socket socketToBeHandle;

    public SocketHandler(Socket socket) {
        this.socketToBeHandle = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socketToBeHandle.getInputStream()));
            out = new PrintWriter(socketToBeHandle.getOutputStream(), true);
            while (true) {
                String s = in.readLine();
                if (StringUtils.isNullOrEmpty(s)) {
                    break;
                }
                System.out.println(s);
                out.println("当前时间为"+System.currentTimeMillis());
            }
        } catch (Exception e) {

            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            if (out != null) {
                out.close();
                out = null;
            }

        }

    }
}
