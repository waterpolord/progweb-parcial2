package oppucmm.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Embeddable
public class Location implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "longitude")
    private String longitude;
    @Column(name = "latitude")
    private String latitude;

    /*Empty constructor*/
    public Location() {

    }
    /*Principal constructor*/
    public Location(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    /*Getters and Setters*/
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
