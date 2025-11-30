import java.util.ArrayList;
import java.rmi.*;

public interface PlacesListInterface extends Remote{

    public void addPlace(Place p) throws RemoteException;
    public ArrayList<Place> allPlaces() throws RemoteException;
}
