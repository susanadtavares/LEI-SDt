import java.io.Serializable;

public class Place implements Serializable {

    private String postalCode;

    private String locality;

    public Place(String postalCode, String locality){
        this.locality = locality;
        this.postalCode = postalCode;
    }

    public String getPostalCode(){
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocality(){return locality;}

    public void setLocality(String locality) {
        this.locality = locality;
    }
}
