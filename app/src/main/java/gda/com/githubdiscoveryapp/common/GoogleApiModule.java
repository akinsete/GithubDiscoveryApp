package gda.com.githubdiscoveryapp.common;

import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.view.Display;
import android.view.WindowManager;

import com.google.android.gms.common.GoogleApiAvailability;
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
public class GoogleApiModule {

    private final Context mContext;

    public GoogleApiModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    public GoogleApiClient provideGoogleApiClient() {
        return new GoogleApiClient.Builder(mContext).addApi(LocationServices.API).build();
    }

    @Provides
    @Singleton
    public GoogleApiAvailability provideGoogleApiAvailability() {
        return GoogleApiAvailability.getInstance();
    }

    @SuppressWarnings("SameReturnValue")
    @Provides
    @Singleton
    public FusedLocationProviderApi provideFusedLocationProviderApi() {
        return LocationServices.FusedLocationApi;
    }

    @Provides
    @Singleton
    public LocationManager provideLocationManager() {
        return (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
    }

}