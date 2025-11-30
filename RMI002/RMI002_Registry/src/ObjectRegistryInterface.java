import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObjectRegistryInterface extends Remote {
    void addObject(String postal, String address) throws RemoteException;

    String resolve(String postal) throws RemoteException;
}