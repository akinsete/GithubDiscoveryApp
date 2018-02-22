package gda.com.githubdiscoveryapp;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sundayakinsete on 21/02/2018.
 */


@Module
public class ApplicationModule {

    private Application application;


    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return application;
    }


}
