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
    }

    public ArrayList<Place> allPlaces() throws RemoteException {
        return placeList;
    }
}