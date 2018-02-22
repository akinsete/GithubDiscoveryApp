package gda.com.githubdiscoveryapp.searchuser;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public class Search {


    private String username;
    private long date;
    private long latitude;
    private long longitude;
    private String address;


    public Search(String username, long latitude, long longitude, String address) {
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

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
