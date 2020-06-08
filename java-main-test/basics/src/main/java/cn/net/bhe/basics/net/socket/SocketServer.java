package cn.net.bhe.basics.net.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketServer {
    static final Logger log = LoggerFactory.getLogger(SocketServer.class);
    List<PrintWriter> printWriters = new ArrayList<>();
    List<Socket> clients = new ArrayList<Socket>();

    /**
     * 服务端简单实现
     */
    @Test
    public void test() {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            log.info("服务端启动，端口：{}", serverSocket.getLocalPort());
            
            while (printWriters.size() < 10) {
                Socket client = serverSocket.accept(); // 获得连接请求前会保持阻塞
                log.info("与客户端{}建立连接", client.getRemoteSocketAddress());
                
                clients.add(client);
                PrintWriter pWriter = new PrintWriter(client.getOutputStream());
                printWriters.add(pWriter);
                
                new Thread(new ClientHandler(client)).start();
            }
            
            Thread.currentThread().join();
            serverSocket.close();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public class ClientHandler implements Runnable {
        BufferedReader bfReader;
        Socket client;

        public ClientHandler(Socket client) {
            try {
                this.client = client;
                InputStreamReader isReader = new InputStreamReader(client.getInputStream());
                bfReader = new BufferedReader(isReader);
            } catch (Exception e) {
                log.error("", e);
            }
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = bfReader.readLine()) != null) {
                    log.info("收到客户端{}消息：{}", client.getRemoteSocketAddress(), message);
                    tellEveryone(message);
                }
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    public void tellEveryone(String message) {
        for (int i = 0; i < printWriters.size(); i++) {
            try {
                PrintWriter pWriter = printWriters.get(i);
                pWriter.println(message);
                log.info("向客户端{}群发消息：{}", clients.get(i).getRemoteSocketAddress(), message);
                pWriter.flush();
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }
}
