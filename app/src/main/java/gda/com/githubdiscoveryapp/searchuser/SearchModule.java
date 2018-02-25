package gda.com.githubdiscoveryapp.searchuser;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gda.com.githubdiscoveryapp.data.geocoder.GeocodeAPI;
import gda.com.githubdiscoveryapp.data.geocoder.GeocoderService;
import gda.com.githubdiscoveryapp.data.github.GithubAPI;
import gda.com.githubdiscoveryapp.data.github.GithubService;

/**
 * Created by sundayakinsete on 21/02/2018.
 */



@Module
public class SearchModule {

    @Provides
    public SearchActivityMVP.Presenter provideSearchActivityPresenter(SearchActivityMVP.Model model){
        return new SearchActivityPresenter(model);
    }


    @Provides
    public SearchActivityMVP.Model provideSearchActivityModel(Repository repository){
        return new SearchModel(repository);
    }


    @Provides
    public Repository provideRepository(GithubAPI githubAPI,GeocodeAPI geocodeApi,LocalDatabaseHelper localDatabaseHelperApi){
        return new SearchRepository(githubAPI,geocodeApi,localDatabaseHelperApi);
    }

    @Provides
    @Singleton
    public LocalDatabaseHelper provideLocalDatabaseHelper(Context context){
        return new LocalDatabaseHelper(context);
    }

}
