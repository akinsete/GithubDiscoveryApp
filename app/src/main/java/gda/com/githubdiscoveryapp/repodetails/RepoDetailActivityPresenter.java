package gda.com.githubdiscoveryapp.repodetails;

import java.util.List;

import gda.com.githubdiscoveryapp.data.github.GithubService;
import gda.com.githubdiscoveryapp.data.models.Issue;
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
    public void buttonViewOnGithubClicked() {
        if(view != null){
            view.goViewRepoOnGithub();
        }
    }

    @Override
    public void setView(RepoDetailActivityMVP.View view) {
        this.view = view;
    }


    @Override
    public void loadRepoIssues(String name, String repo_name) {
        model.getRepoIssues(name, repo_name, new GithubService.RepoIssuesCallback() {
            @Override
            public void onSuccess(List<Issue> issues) {
                if(view != null){
                    view.displayRepoIssues(issues);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

}
