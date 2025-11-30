import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PlacesListInterface extends Remote{

    public void addPlace(Place p) throws RemoteException;

    Place getPlace(String postal) throws RemoteException;
    public ArrayList<Place> placeList() throws RemoteException;
}
