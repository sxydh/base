package cn.net.bhe.basics.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HowAreYou extends Remote {
    String sayHowAreYou() throws RemoteException;
}
