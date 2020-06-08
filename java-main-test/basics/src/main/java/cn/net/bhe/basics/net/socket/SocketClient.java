package cn.net.bhe.basics.net.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketClient {
    static final Logger log = LoggerFactory.getLogger(SocketClient.class);

    /**
     * 客户端简单实现
     */
    @Test
    public void test() {
        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            log.info("客户端启动，端口：{}", socket.getPort());
            
            // 接收消息
            new Thread() {
                @Override
                public void run() {
                    try {
                        InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                        BufferedReader bfReader = new BufferedReader(isReader);
                        while (!socket.isClosed()) {
                            String msg = bfReader.readLine();
                            log.info("从{}获得消息：{}", socket.getRemoteSocketAddress(), msg);
                        }
                    } catch (Exception e) {
                        log.error("", e);
                    }
                }
            }.start();
            
            // 发送消息
            new Thread() {
                @Override
                public void run() {
                    try {
                        PrintWriter pWriter = new PrintWriter(socket.getOutputStream());
                        @SuppressWarnings("resource")
                        Scanner input = new Scanner(System.in);
                        while (true) {
                            pWriter.println(input.nextLine());
                            pWriter.flush();
                        }
                    } catch (Exception e) {
                        log.error("", e);
                    } 
                }
            }.start();
            
            Thread.currentThread().join();
            socket.close();
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
