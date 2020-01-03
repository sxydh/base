package cn.net.bhe.basics.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(null, 7070);
            Hello hello = (Hello) registry.lookup("Hello");
            System.out.printf("%-15s%s%n", "response:", hello.sayHello());
            HowAreYou howAreYou = (HowAreYou) registry.lookup("HowAreYou");
            System.out.printf("%-15s%s%n", "response:", howAreYou.sayHowAreYou());
            Goodbye goodbye = (Goodbye) registry.lookup("Goodbye");
            System.out.printf("%-15s%s%n", "response:", goodbye.sayGoodbye());
        } catch (Exception e) {
            System.err.printf("%-15s%s%n", "Client:", e.getLocalizedMessage());
        }
    }
}
