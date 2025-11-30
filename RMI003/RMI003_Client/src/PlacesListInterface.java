import java.rmi.*;
import java.util.ArrayList;

public interface PlacesListInterface extends Remote{
    public void addPlace(Place p) throws RemoteException;
    public ArrayList<Place> allPlaces() throws RemoteException;
}
