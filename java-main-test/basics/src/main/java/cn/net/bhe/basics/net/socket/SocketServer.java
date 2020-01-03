package cn.net.bhe.basics.net.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer {

    ArrayList<PrintWriter> agentPrintWriters = new ArrayList<>();

    public static void main(String[] args) {
        new SocketServer().start();
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.printf("%-20s%-10s%n", "server start at:", serverSocket.getLocalSocketAddress());
            int clients = 0;
            while (clients < 10) {
                //the method blocks until a connection is made
                Socket agent = serverSocket.accept();
                System.out.printf("%-30s%-10s%n", "create conn with client:", agent.getRemoteSocketAddress());
                PrintWriter pWriter = new PrintWriter(agent.getOutputStream());
                agentPrintWriters.add(pWriter);
                Thread t = new Thread(new AgentHandler(agent));
                t.start();
                clients++;
            }
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class AgentHandler implements Runnable {
        BufferedReader bfReader;
        Socket agentSocket;

        public AgentHandler(Socket agentSocket) {
            try {
                this.agentSocket = agentSocket;
                InputStreamReader isReader = new InputStreamReader(agentSocket.getInputStream());
                bfReader = new BufferedReader(isReader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            String message;
            try {
                //the method blocks until data is not empty
                while ((message = bfReader.readLine()) != null) {
                    System.out.printf("%-10s%n%-20s%n%-10s%n", "get msg:", message, "from client:" + agentSocket.getRemoteSocketAddress());
                    tellEveryone(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void tellEveryone(String message) {
        for (PrintWriter pWriter : agentPrintWriters) {
            try {
                pWriter.println(message);
                pWriter.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
