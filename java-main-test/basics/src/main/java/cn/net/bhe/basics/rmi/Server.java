package cn.net.bhe.basics.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String args[]) {
        try {
            Hello hello = (Hello) UnicastRemoteObject.exportObject(new Hello() {

                @Override
                public String sayHello() throws RemoteException {
                    return "Hello!";
                }

            }, 7080);
            HowAreYou howAreYou = (HowAreYou) UnicastRemoteObject.exportObject(new HowAreYou() {

                @Override
                public String sayHowAreYou() throws RemoteException {
                    return "How are you!";
                }

            }, 7080);
            Goodbye goodbye = (Goodbye) UnicastRemoteObject.exportObject(new Goodbye() {

                @Override
                public String sayGoodbye() throws RemoteException {
                    return "Goodbye!";
                }

            }, 7080);
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(7070);
            registry.bind("Hello", hello);
            registry.bind("HowAreYou", howAreYou);
            registry.bind("Goodbye", goodbye);
            System.out.printf("%-15s%s%n", "Server:", "ready");
        } catch (Exception e) {
            System.err.printf("%-15s%s%n", "Server:", e.getLocalizedMessage());
        }
    }
}
