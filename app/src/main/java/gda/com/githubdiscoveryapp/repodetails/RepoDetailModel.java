package gda.com.githubdiscoveryapp.repodetails;

import gda.com.githubdiscoveryapp.data.github.GithubService;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public class RepoDetailModel implements RepoDetailActivityMVP.Model{


    GithubService githubService;

    public RepoDetailModel(GithubService githubService) {
        this.githubService = githubService;
    }

    @Override
    public void getRepoIssues(String username, String repo, GithubService.RepoIssuesCallback repoIssuesCallback) {
        githubService.getRepoIssues(username,repo,repoIssuesCallback);
    }
}
