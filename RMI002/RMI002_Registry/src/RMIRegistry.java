import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIRegistry {

    public static void main(String[] args) {
        Registry r = null;

        try {
            r = LocateRegistry.createRegistry(2023);
        } catch (RemoteException a) {
            a.printStackTrace();
        }

        try {
            ObjectRegistry registry = new ObjectRegistry();
            r.rebind("registry", registry);
            System.out.println("Registry server ready");
        } catch (Exception var3) {
            System.out.println("Registry server main " + var3.getMessage());
        }

    }
}