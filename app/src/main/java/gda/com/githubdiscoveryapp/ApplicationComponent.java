package gda.com.githubdiscoveryapp;

import javax.inject.Singleton;

import dagger.Component;
import gda.com.githubdiscoveryapp.common.GoogleApiModule;
import gda.com.githubdiscoveryapp.location.LocationModule;
import gda.com.githubdiscoveryapp.searchuser.SearchActivity;
import gda.com.githubdiscoveryapp.searchuser.SearchActivityPresenter;
import gda.com.githubdiscoveryapp.searchuser.SearchModule;

/**
 * Created by sundayakinsete on 21/02/2018.
 */


@Singleton
@Component(modules = {ApplicationModule.class, SearchModule.class,LocationModule.class})
public interface ApplicationComponent {

    void inject (SearchActivity target);

    //void inject (SearchActivityPresenter presenter);

}

//@Singleton
//public interface LocationProviderFactoryComponent {
//    LocationProviderFactory locationProviderFactory();
//}