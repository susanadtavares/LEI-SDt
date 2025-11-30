import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ReplicaManager extends UnicastRemoteObject implements PlacesListManagerInterface {
    ObjectRegistryInterface ORI = null;
    ArrayList<String> placesList = new ArrayList();

    protected ReplicaManager() throws RemoteException {
        this.placesList.add("rmi://localhost:2025/placelist");
        this.placesList.add("rmi://localhost:2026/placelist");
        this.placesList.add("rmi://localhost:2027/placelist");
    }

    public void addPlace(Place p) throws RemoteException {
        PlacesListInterface l = null;

        for(int i = 0; i < this.placesList.size(); ++i) {
            try {
                l = (PlacesListInterface) Naming.lookup((String)this.placesList.get(i));
                l.addPlace(p);
            } catch (MalformedURLException malErr) {
                malErr.printStackTrace();
            } catch (NotBoundException nbErr) {
                nbErr.printStackTrace();
            }
        }

        try {
            this.ORI = (ObjectRegistryInterface)Naming.lookup("rmi://localhost:2023/registry");
            this.ORI.addRManager(p.getPostalCode(), "rmi://localhost:2024/replicamanager");
        } catch (MalformedURLException var5) {
            var5.printStackTrace();
        } catch (NotBoundException var6) {
            var6.printStackTrace();
        }

    }

    public String getPlaceListAddress(String objectID) throws RemoteException {
        return (String)this.placesList.get((int)(Math.random() * 3.0D));
    }
}