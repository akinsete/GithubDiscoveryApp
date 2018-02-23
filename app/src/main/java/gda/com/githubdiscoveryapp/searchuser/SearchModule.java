package gda.com.githubdiscoveryapp.searchuser;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import gda.com.githubdiscoveryapp.data.geocoder.GeocoderService;
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
    public SearchActivityMVP.Model provideSearchActivityModel(SearchRepository searchRepository, GeocoderService geocoderService, GithubService githubService){
        return new SearchModel(searchRepository,geocoderService,githubService);
    }


    @Provides
    public SearchRepository provideSearchRepository(Context context){
        return new MemoryRepository(context);
    }



}
