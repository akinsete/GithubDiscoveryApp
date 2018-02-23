package gda.com.githubdiscoveryapp.usersrepolist;

import android.support.annotation.Nullable;

import gda.com.githubdiscoveryapp.data.models.Repo;
import gda.com.githubdiscoveryapp.searchuser.SearchActivityMVP;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public class UserRepoListActivityPresenter implements UserRepoListActivityMVP.Presenter{

    @Nullable
    private UserRepoListActivityMVP.View view;
    private UserRepoListActivityMVP.Model model;


    ///// Constructor injector ///
    public UserRepoListActivityPresenter(UserRepoListActivityMVP.Model model) {
        this.model = model;
    }


    @Override
    public void setView(UserRepoListActivityMVP.View view) {
        this.view = view;
    }


    @Override
    public void navigateToDetailView(Repo repo) {
        if(view != null){
            view.gotoDetailView(repo);
        }
    }


}
