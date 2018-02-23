package gda.com.githubdiscoveryapp.data.github;

import android.util.Log;

import java.util.List;

import gda.com.githubdiscoveryapp.data.geocoder.GeocoderService;
import gda.com.githubdiscoveryapp.data.models.GeocodeResult;
import gda.com.githubdiscoveryapp.data.models.Issue;
import gda.com.githubdiscoveryapp.data.models.Repo;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by sundayakinsete on 22/02/2018.
 */

public class GithubService {

    private static final String TAG = "GithubService";

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
                        Log.e(TAG, String.valueOf(value));
                        callback.onSuccess(value);
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
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
        Log.e(TAG, String.valueOf(username));
        Log.e(TAG, String.valueOf(repo_name));

        githubApi.listRepoIssues(username,repo_name).subscribeOn(rx.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new rx.Observer<List<Issue>>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onNext(List<Issue> value) {
                        Log.e(TAG, String.valueOf(value));
                        callback.onSuccess(value);
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
                        callback.onError();
                    }

                });
    }
}
