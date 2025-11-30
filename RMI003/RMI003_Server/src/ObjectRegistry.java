import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ObjectRegistry extends UnicastRemoteObject implements ObjectRegistryInterface {
    private static final long serialVersionUID = 1L;
    static HashMap<String, String> hm = new HashMap();

    protected ObjectRegistry() throws RemoteException {
    }

    public void addObject(String objectID, String serverAddress) throws RemoteException {
        hm.put(objectID, serverAddress);
    }

    public String resolve(String objectID) throws RemoteException {
        return (String)hm.get(objectID);
    }

    public void addRManager(String objectID, String serverAddress) throws RemoteException {
        hm.put(objectID, serverAddress);
    }
}