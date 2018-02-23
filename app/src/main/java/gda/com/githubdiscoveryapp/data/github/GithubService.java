package gda.com.githubdiscoveryapp.data.github;

import java.util.List;

import gda.com.githubdiscoveryapp.data.geocoder.GeocoderService;
import gda.com.githubdiscoveryapp.data.models.GeocodeResult;
import gda.com.githubdiscoveryapp.data.models.Issue;
import gda.com.githubdiscoveryapp.data.models.Repo;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sundayakinsete on 22/02/2018.
 */

public class GithubService {

    private final GithubAPI githubApi;

    public GithubService(GithubAPI githubApi) {
        this.githubApi = githubApi;
    }


    public interface getUserRepoListCallBack{
        void onSuccess(List<Repo> repos);

        void onError();
    }

    public interface RepoIssuesCallback{
        void onSuccess(List<Issue> issues);

        void onError();
    }


    /**
     * An observer for fetching user repos
     * @param username
     * @param callback
     */
    public void getUserRepo(String username,final GithubService.getUserRepoListCallBack callback){
        githubApi.listRepos(username).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Repo> value) {
                        callback.onSuccess(value);
                    }


                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * Observer for fetching repo issues
     * @param username
     * @param repo_name
     * @param callback
     */
    public void getRepoIssues(String username,String repo_name,final RepoIssuesCallback callback){
        githubApi.listRepoIssues(username,repo_name).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<List<Issue>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Issue> value) {
                        callback.onSuccess(value);
                    }


                    @Override
                    public void onError(Throwable e) {
                        callback.onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
