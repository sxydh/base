package cn.net.bhe.basics.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Goodbye extends Remote {
    String sayGoodbye() throws RemoteException;
}
