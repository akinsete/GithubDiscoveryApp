package gda.com.githubdiscoveryapp.common;

import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sundayakinsete on 22/02/2018.
 */

@Module
public class LocationModule {


    private final GoogleApiClient.ConnectionCallbacks callback;
    private final GoogleApiClient.OnConnectionFailedListener listener;

    public LocationModule(GoogleApiClient.ConnectionCallbacks callback, GoogleApiClient.OnConnectionFailedListener listener) {
        this.callback = callback;
        this.listener = listener;
    }

    @Provides
    @Singleton
    GoogleApiClient providesGoogleApi(Context context) {
        return new GoogleApiClient.Builder(context)
                .addOnConnectionFailedListener(listener)
                .addConnectionCallbacks(callback)
                .addApi(LocationServices.API)
                .build();
    }
}
