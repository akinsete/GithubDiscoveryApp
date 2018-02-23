package gda.com.githubdiscoveryapp.di;

import javax.inject.Singleton;

import dagger.Component;
import gda.com.githubdiscoveryapp.data.ApiModule;
import gda.com.githubdiscoveryapp.repodetails.RepoDetailActivity;
import gda.com.githubdiscoveryapp.repodetails.RepoDetailModule;
import gda.com.githubdiscoveryapp.searchuser.SearchActivity;
import gda.com.githubdiscoveryapp.searchuser.SearchModule;
import gda.com.githubdiscoveryapp.usersrepolist.RepoModule;
import gda.com.githubdiscoveryapp.usersrepolist.UserRepoListActivity;

/**
 * Created by sundayakinsete on 21/02/2018.
 */


@Singleton
@Component(modules = {ApplicationModule.class, SearchModule.class, ApiModule.class, RepoModule.class,RepoDetailModule.class})
public interface ApplicationComponent {

    void inject (SearchActivity target);

    void inject (UserRepoListActivity target);

    void inject (RepoDetailActivity target);

    //void inject (SearchActivityPresenter presenter);

}

