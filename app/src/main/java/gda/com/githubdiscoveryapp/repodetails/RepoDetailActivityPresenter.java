package gda.com.githubdiscoveryapp.repodetails;

import gda.com.githubdiscoveryapp.searchuser.SearchActivityMVP;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public class RepoDetailActivityPresenter implements RepoDetailActivityMVP.Presenter{

    RepoDetailActivityMVP.View view;
    RepoDetailActivityMVP.Model model;

    public RepoDetailActivityPresenter(RepoDetailActivityMVP.Model model) {
        this.model = model;
    }


    @Override
    public void setView(RepoDetailActivityMVP.View view) {
        this.view = view;
    }
}
