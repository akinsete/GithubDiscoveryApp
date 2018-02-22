package gda.com.githubdiscoveryapp;

import android.app.Application;

import gda.com.githubdiscoveryapp.location.LocationModule;
import gda.com.githubdiscoveryapp.searchuser.SearchModule;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public class App extends Application{

    private ApplicationComponent component;


    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .searchModule(new SearchModule())
                .locationModule(new LocationModule())
                .build();

    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
