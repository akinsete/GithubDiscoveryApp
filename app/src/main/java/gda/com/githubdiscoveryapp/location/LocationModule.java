package gda.com.githubdiscoveryapp.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sundayakinsete on 22/02/2018.
 */

@Module
public class LocationModule{

    GoogleApiClient.ConnectionCallbacks callbacks;
    GoogleApiClient.OnConnectionFailedListener listener;


//    public LocationModule(GoogleApiClient.ConnectionCallbacks callback, GoogleApiClient.OnConnectionFailedListener listener) {
//        this.callbacks = callback;
//        this.listener = listener;
//    }


    @Provides
    @Singleton
    GoogleApiClient providesGoogleApi(Context context, GoogleApiClient.ConnectionCallbacks callback, GoogleApiClient.OnConnectionFailedListener listener) {
        return new GoogleApiClient.Builder(context)
                .addOnConnectionFailedListener(listener)
                .addConnectionCallbacks(callback)
                .addApi(LocationServices.API)
                .build();
    }

    @SuppressWarnings("SameReturnValue")
    @Provides
    @Singleton
    public FusedLocationProviderApi provideFusedLocationProviderApi() {
        return LocationServices.FusedLocationApi;
    }

    @Provides
    @Singleton
    public LocationManager provideLocationManager(Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

//    @SuppressWarnings("SameReturnValue")
//    @Provides
//    @Singleton
//    public GoogleApiClient.ConnectionCallbacks provideConnectionCallbacks() {
//        return new GoogleApiClient.ConnectionCallbacks.
//    }

}
