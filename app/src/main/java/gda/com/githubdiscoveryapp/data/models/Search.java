package gda.com.githubdiscoveryapp.data.models;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public class Search {


    private String username;
    private long date;
    private String latitude;
    private String longitude;
    private String address;


    public Search(String username, String latitude, String longitude, String address) {
        this.username = username;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
