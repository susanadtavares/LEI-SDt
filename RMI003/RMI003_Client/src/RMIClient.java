import javax.management.remote.rmi.RMIServer;
import java.rmi.*;
import java.util.ArrayList;

public class RMIClient {

    public static void main(String[] args) throws InterruptedException {
        PlacesListInterface l = null;
        PlacesListManagerInterface lm = null;
        ObjectRegistryInterface r = null;

        Thread t = (new Thread() {
            public void run() {
                RMIRegistry.main(new String[0]);
                RMIReplicaManager.main(new String[0]);
                RMIServer.main(new String[]{"2025"});
                RMIServer.main(new String[]{"2026"});
                RMIServer.main(new String[]{"2027"});
            }
        });
        t.start();
        Thread.sleep(1000);
        try{
            l  = (PlacesListInterface) Naming.lookup("rmi://localhost:2022/placelist");
            lm = (PlacesListManagerInterface) Naming.lookup("rmi://localhost:2024/replicamanager");
            r = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:2023/registry");
            Place p = new Place("3500", "Viseu");
            lm.addPlace(p);

            ArrayList<Place> sList = l.allPlaces();

            sList.forEach((n) -> System.out.println(n.getLocality()));
        } catch(RemoteException e) {
            System.out.println(e.getMessage());
        }catch(Exception e) {e.printStackTrace();}
    }
}