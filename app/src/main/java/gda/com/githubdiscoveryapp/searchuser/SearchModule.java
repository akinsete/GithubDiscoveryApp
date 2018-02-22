package gda.com.githubdiscoveryapp.searchuser;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import gda.com.githubdiscoveryapp.App;

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
    public SearchActivityMVP.Model provideSearchActivityModel(SearchRepository searchRepository){
        return new SearchModel(searchRepository);
    }


    @Provides
    public SearchRepository provideSearchRepository(Context context){
        return new MemoryRepository(context);
    }
}
