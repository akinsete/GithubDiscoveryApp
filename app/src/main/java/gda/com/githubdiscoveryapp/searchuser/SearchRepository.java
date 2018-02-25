package gda.com.githubdiscoveryapp.searchuser;

import android.util.Log;

import java.util.HashMap;
import java.util.List;

import gda.com.githubdiscoveryapp.data.geocoder.GeocodeAPI;
import gda.com.githubdiscoveryapp.data.github.GithubAPI;
import gda.com.githubdiscoveryapp.data.models.GeocodeResult;
import gda.com.githubdiscoveryapp.data.models.Repo;
import gda.com.githubdiscoveryapp.data.models.Result;
import gda.com.githubdiscoveryapp.data.models.Search;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by sundayakinsete on 25/02/2018.
 */

public class SearchRepository implements Repository,LocaldatabaseRepository {

    private GithubAPI githubApi;
    private GeocodeAPI geocodeApi;
    private LocalDatabaseHelper localDatabaseHelperApi;
    private HashMap<String,List<Repo>> reposCache = new HashMap<>();
    private HashMap<String,Result> addressCache = new HashMap<>();
    private HashMap<String,Long> timestamps = new HashMap<>();

    private static final long STALE_MS = 40 * 1000; // Data is stale after 20 seconds

    public SearchRepository(GithubAPI githubApi,GeocodeAPI geocodeApi,LocalDatabaseHelper localDatabaseHelperApi) {
        this.githubApi = githubApi;
        this.geocodeApi = geocodeApi;
        this.localDatabaseHelperApi = localDatabaseHelperApi;
    }

    /**
     * Check to see if cached data can still be used
     * @param key
     * @return
     */
    private boolean isUpToDate(String key) {
        if(timestamps.get(key) == null){
            return false;
        }
        return System.currentTimeMillis() - timestamps.get(key) < STALE_MS;
    }

    /**
     * Fetch previously stored searches from local database
     * @return
     */
    @Override
    public List<Search> getPreviousSearches() {
        return localDatabaseHelperApi.getPreviousSearches();
    }

    /**
     * sacePreviously Stored searches
     * @param search
     */
    @Override
    public void saveSearch(Search search) {
        localDatabaseHelperApi.saveSearch(search);
    }


    /**
     * Fetch, if valid results from memory
     * @param user
     * @return
     */
    @Override
    public Observable<List<Repo>> getSearchResultsFromMemory(String user) {
        Log.e("Tag","getSearchResultsFromMemory");
        if (isUpToDate(user)) {
            return Observable.just(reposCache.get(user));
        } else {
            timestamps.put(user,System.currentTimeMillis());
            reposCache.remove(user);
            return Observable.empty();
        }
    }

    /**
     * Fetch result from the the server
     * @param user
     * @return
     */
    @Override
    public Observable<List<Repo>> getSearchResultsFromNetwork(final String user) {
        return githubApi
                .listRepos(user).asObservable()
                .doOnNext(new Action1<List<Repo>>() {
            @Override
            public void call(List<Repo> repos) {
                reposCache.put(user,repos);
            }
        });
    }


    /**
     * Get geocodded address from memory if still valid
     * @param location
     * @return
     */
    @Override
    public Observable<Result> getGeocodedAddressFromMemory(String location) {
        if (isUpToDate(location)) {
            return Observable.just(addressCache.get(location));
        } else {
            timestamps.put(location,System.currentTimeMillis());
            addressCache.remove(location);
            return Observable.empty();
        }
    }


    /**
     * Fetch geocoded address from network and store response in a cache with search location as key.
     * @param location
     * @return
     */
    @Override
    public Observable<Result> getGeocodedAddressFromNetwork(final String location) {
        return geocodeApi.reverseGeocodeObservables(location).map(new Func1<GeocodeResult, Result>() {
            @Override
            public Result call(GeocodeResult geocodeResult) {
                return geocodeResult.getResults().get(0);
            }
        }).doOnNext(new Action1<Result>() {
            @Override
            public void call(Result result) {
                addressCache.put(location,result);
            }
        });
    }

}
