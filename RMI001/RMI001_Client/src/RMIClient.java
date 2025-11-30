import java.rmi.*;
import java.util.ArrayList;

public class RMIClient {

    public static void main(String[] args) {
        PlacesListInterface l = null;
        try{
            l  = (PlacesListInterface) Naming.lookup("rmi://localhost:2022/placelist");
            Place p = new Place("3730", "Vale de Cambra");
            l.addPlace(p);

            ArrayList<Place> sList = l.allPlaces();

            sList.forEach((n) -> System.out.println(n.getLocality()));
        } catch(RemoteException e) {
            System.out.println(e.getMessage());
        }catch(Exception e) {e.printStackTrace();}
    }
}