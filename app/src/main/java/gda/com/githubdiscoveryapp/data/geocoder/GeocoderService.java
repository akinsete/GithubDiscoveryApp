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



    public interface GetFormattedAddressCallback{
        void onSuccess(String address);
    }

    public void  getFormattedAddress(String location,final GetFormattedAddressCallback callback){
         geocodeAPI.reverseGeocodeObservables(location)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io()).subscribe(new Observer<GeocodeResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GeocodeResult value) {
                        Log.i("geoResults",value.toString());
                        callback.onSuccess(value.getResults().get(0).getFormattedAddress());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
