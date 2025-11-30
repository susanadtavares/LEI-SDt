import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static Registry r = null;
    public static PlacesManager placeList;

    public static void main (String args[]) {

        try {
            r = LocateRegistry.createRegistry(2022);
        } catch (RemoteException a) {
            a.printStackTrace();
        }

        try {
            placeList = new PlacesManager();
            r.rebind("placelist", placeList);

            System.out.println("Place server ready");
        } catch (Exception e) {
            System.out.println("Place server main " + e.getMessage());
        }
    }
}