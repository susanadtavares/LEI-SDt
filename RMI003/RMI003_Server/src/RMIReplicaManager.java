import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIReplicaManager {
    public RMIReplicaManager() {
    }

    public static void main(String[] args) {
        Registry r = null;

        try {
            r = LocateRegistry.createRegistry(2024);
        } catch (RemoteException var4) {
            var4.printStackTrace();
        }

        try {
            Remote replica_manager = new ReplicaManager();
            r.rebind("replicamanager", replica_manager);
            System.out.println("Replica server ready");
        } catch (Exception var3) {
            System.out.println("Replica server main " + var3.getMessage());
        }

    }
}