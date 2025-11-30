import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlacesListManagerInterface extends Remote {
    public void addPlace(Place p) throws RemoteException;
    String getPlaceListAddress(String id) throws RemoteException;
}
