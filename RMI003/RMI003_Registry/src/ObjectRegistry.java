import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ObjectRegistry extends UnicastRemoteObject implements ObjectRegistryInterface {
    static HashMap<String, String> hash = new HashMap();

    protected ObjectRegistry() throws RemoteException {
    }

    public void addObject(String postal, String address) throws RemoteException {
        hash.put(postal, address);
    }

    public String resolve(String postal) throws RemoteException {
        return hash.get(postal);
    }
    public void addRManager(String objectID, String serverAddress) throws RemoteException {
        hash.put(objectID, serverAddress);
    }
}