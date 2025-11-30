import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PlaceManager extends UnicastRemoteObject implements PlacesListInterface {
    private static final long serialVersionUID = 1L;
    private Place place;
    ArrayList<Place> ap = new ArrayList();

    protected PlaceManager() throws RemoteException {
    }

    public void addPlace(Place p) throws RemoteException {
        this.ap.add(p);

        try {
            ObjectRegistryInterface l2 = (ObjectRegistryInterface)Naming.lookup("rmi://localhost:2023/registry");
            l2.addObject(p.getPostalCode(), "rmi://localhost:2022/placelist");
        } catch (MalformedURLException var4) {
            var4.printStackTrace();
        } catch (NotBoundException var5) {
            var5.printStackTrace();
        }
    }

    public ArrayList placeList() throws RemoteException {
        return this.ap;
    }

    public Place getPlace(String c) throws RemoteException {
        for(int i = 0; i <= this.ap.size(); ++i) {
            if (c.equals(((Place)this.ap.get(i)).getPostalCode())) {
                return (Place)this.ap.get(i);
            }
        }

        return null;
    }
}
