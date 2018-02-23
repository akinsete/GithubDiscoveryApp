package gda.com.githubdiscoveryapp.searchuser;

import android.util.Log;

import java.util.List;

import gda.com.githubdiscoveryapp.data.geocoder.GeocoderService;
import gda.com.githubdiscoveryapp.data.github.GithubService;
import gda.com.githubdiscoveryapp.data.models.Repo;
import gda.com.githubdiscoveryapp.data.models.Search;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public class SearchModel implements SearchActivityMVP.Model {

    SearchRepository repository;
    GeocoderService geocoderService;
    GithubService githubService;

    public SearchModel(SearchRepository repository,GeocoderService geocoderService,GithubService githubService) {
        this.repository = repository;
        this.geocoderService = geocoderService;
        this.githubService = githubService;
    }

    @Override
    public void saveSearch(String username, String latitude, String longitude, String address) {
        repository.saveSearch(new Search(username,latitude,longitude,address));
    }

    @Override
    public List<Search> previousSearches() {
        return repository.getPreviousSearches();
    }


    @Override
    public void getGithubRepositories(String username,GithubService.getUserRepoListCallBack callBack) {
        githubService.getUserRepo(username,callBack);
    }

    @Override
    public void getReverseGeocodedAddress(String location, GeocoderService.GetFormattedAddressCallback callback) {
        geocoderService.getFormattedAddress(location,callback);
    }
}
