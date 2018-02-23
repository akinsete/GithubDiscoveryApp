package gda.com.githubdiscoveryapp.data.geocoder;

import java.util.List;

import gda.com.githubdiscoveryapp.BuildConfig;
import gda.com.githubdiscoveryapp.data.models.GeocodeResult;
import gda.com.githubdiscoveryapp.data.models.Repo;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static gda.com.githubdiscoveryapp.data.Constants.GoolgeAPIKey;

/**
 * Created by sundayakinsete on 22/02/2018.
 */

public interface GeocodeAPI {

    @GET("geocode/json?key=AIzaSyAoASds49kqY_3y61wrhBbmFK14yNxS5GA")
    rx.Observable<GeocodeResult> reverseGeocodeObservables(@Query("latlng") String location);

}
