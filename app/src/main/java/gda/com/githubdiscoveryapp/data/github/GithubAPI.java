package gda.com.githubdiscoveryapp.data.github;

import java.util.List;

import gda.com.githubdiscoveryapp.data.models.Issue;
import gda.com.githubdiscoveryapp.data.models.Repo;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sundayakinsete on 22/02/2018.
 */

public interface GithubAPI {

    @GET("users/{user}/repos")
    Observable<List<Repo>> listRepos(@Path("user") String user);

    @GET("repos/{user}/{repo}/issues")
    rx.Observable<List<Issue>> listRepoIssues(@Path("user") String user, @Path("repo") String repo);
}
