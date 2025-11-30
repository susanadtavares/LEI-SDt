import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PlacesManager extends UnicastRemoteObject implements PlacesListInterface{

    private ArrayList<Place> placeList = new ArrayList<Place>();

    protected PlacesManager() throws RemoteException {
    }

    public PlacesManager(ArrayList<Place> placeList) throws RemoteException{
        this.placeList=placeList;
    }

    public void addPlace(Place p) throws RemoteException {
        this.placeList.add(p);

        try {
            ObjectRegistryInterface OI = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:2023/registry");
            OI.addObject(p.getPostalCode(), "rmi://localhost:2022/placelist");
        } catch (NotBoundException | MalformedURLException var4) {
            System.out.println(var4.getCause());
        }

    }
    public Place getPlace(String postal) throws RemoteException {
        for(int i = 0; i < this.placeList.size(); ++i) {
            if (postal.equals(((Place)this.placeList.get(i)).getPostalCode())) {
                return (Place)this.placeList.get(i);
            }
        }
        return null;
    }

    public ArrayList<Place> placeList() throws RemoteException {
        return placeList;
    }
}