package gda.com.githubdiscoveryapp.usersrepolist;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sundayakinsete on 23/02/2018.
 */


@Module
public class RepoModule {


    @Provides
    public UserRepoListActivityMVP.Presenter provideRepoListActivityPresenter(UserRepoListActivityMVP.Model model){
        return new UserRepoListActivityPresenter(model);
    }

    @Provides
    public UserRepoListActivityMVP.Model provideUserRepoListModel(){
        return new UserRepoListModel();
    }

}

