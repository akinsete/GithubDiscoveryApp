package gda.com.githubdiscoveryapp.location;

import android.location.Location;

/**
 * Created by sundayakinsete on 22/02/2018.
 */

public interface LocationCallbacks {

    void handleNewLocation(Location location);

    void handleLocationNotAvailable();
}
