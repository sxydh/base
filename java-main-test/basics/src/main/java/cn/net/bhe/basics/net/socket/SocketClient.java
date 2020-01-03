package cn.net.bhe.basics.net.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import cn.net.bhe.utils.main.MathUtils;

public class SocketClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            System.out.printf("%-20s%-10s%n", "client " + MathUtils.randomNum(3), "start");
            new Thread() {
                @Override
                public void run() {
                    try {
                        InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                        BufferedReader bfReader = new BufferedReader(isReader);
                        while (true) {
                            System.out.printf("%-10s%n%-20s%n%-10s%n", "get msg:", bfReader.readLine(), "from server:" + socket.getRemoteSocketAddress());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            PrintWriter pWriter = new PrintWriter(socket.getOutputStream());
            sendMessage(pWriter);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(PrintWriter pWriter) {
        try {
            while (true) {
                BufferedReader bfReader = new BufferedReader(new InputStreamReader(System.in));
                String message = bfReader.readLine();
                pWriter.println(message);
                pWriter.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
