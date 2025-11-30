import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public RMIServer() {
    }

    public static void main(String[] args) {
        Registry r = null;

        try {
            r = LocateRegistry.createRegistry(Integer.parseInt(args[0]));
        } catch (RemoteException var4) {
            var4.printStackTrace();
        }

        try {
            PlaceManager placeList = new PlaceManager();
            r.rebind("placelist", placeList);
            System.out.println("Place server ready");
        } catch (Exception var3) {
            System.out.println("Place server main " + var3.getMessage());
        }
    }
}
