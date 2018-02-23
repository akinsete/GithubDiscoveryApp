package gda.com.githubdiscoveryapp.di;

import android.app.Application;

import gda.com.githubdiscoveryapp.data.ApiModule;
import gda.com.githubdiscoveryapp.repodetails.RepoDetailModule;
import gda.com.githubdiscoveryapp.searchuser.SearchModule;
import gda.com.githubdiscoveryapp.usersrepolist.RepoModule;

//import gda.com.githubdiscoveryapp.DaggerApplicationComponent;
//import gda.com.githubdiscoveryapp.data.ApiModule;
//import gda.com.githubdiscoveryapp.searchuser.SearchModule;
//import gda.com.githubdiscoveryapp.usersrepolist.RepoModule;

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
                .apiModule(new ApiModule())
                .repoModule(new RepoModule())
                .repoDetailModule(new RepoDetailModule())
                .build();

    }

    public ApplicationComponent getComponent(){
        return component;
    }
}
