package gda.com.githubdiscoveryapp.data.geocoder;

import android.util.Log;

import gda.com.githubdiscoveryapp.data.models.GeocodeResult;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by sundayakinsete on 22/02/2018.
 */

public class GeocoderService {

    private final GeocodeAPI geocodeAPI;

    public GeocoderService(GeocodeAPI geocodeAPI) {
        this.geocodeAPI = geocodeAPI;
    }


    /**
     * Geocoder service call back interface
     */
    public interface GetFormattedAddressCallback{
        void onSuccess(String address);

        void onError(String error);
    }

    /**
     * Gte formatted address
     * @param location
     * @param callback
     */
    public void  getFormattedAddress(String location,final GetFormattedAddressCallback callback){
         geocodeAPI.reverseGeocodeObservables(location)
                .subscribeOn(rx.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new rx.Observer<GeocodeResult>() {
                    @Override
                    public void onNext(GeocodeResult value) {
                        Log.i("geoResults",value.toString());
                        if(value.getResults() != null && value.getResults().size() > 0){
                            callback.onSuccess(value.getResults().get(0).getFormattedAddress());
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.toString());
                    }
                });

    }
}
