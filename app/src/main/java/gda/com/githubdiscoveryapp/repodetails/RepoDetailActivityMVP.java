package gda.com.githubdiscoveryapp.repodetails;

import java.util.List;

import gda.com.githubdiscoveryapp.data.github.GithubService;
import gda.com.githubdiscoveryapp.data.models.Issue;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public interface RepoDetailActivityMVP {


    interface View{

        void goViewRepoOnGithub();

        void displayRepoIssues(List<Issue> issues);
    }

    interface Presenter{

        void buttonViewOnGithubClicked();

        void setView(RepoDetailActivityMVP.View view);

        void loadRepoIssues(String name,String repo_name);
    }


    interface Model{
        void getRepoIssues(String username,String repo,GithubService.RepoIssuesCallback repoIssuesCallback);

    }
    
}
