package gda.com.githubdiscoveryapp.searchuser;

import java.util.List;

import gda.com.githubdiscoveryapp.data.geocoder.GeocoderService;
import gda.com.githubdiscoveryapp.data.github.GithubService;
import gda.com.githubdiscoveryapp.data.models.Repo;
import gda.com.githubdiscoveryapp.data.models.Result;
import gda.com.githubdiscoveryapp.data.models.Search;
import rx.Observable;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public class SearchModel implements SearchActivityMVP.Model {

    Repository repository;

    public SearchModel(Repository repository) {
        this.repository = repository;
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
    public Observable<Result> getReversedGeocodedAddress(String location) {
        return repository.getGeocodedAddressFromMemory(location).switchIfEmpty(repository.getGeocodedAddressFromNetwork(location));
    }

    @Override
    public Observable<List<Repo>> getUserGithubRepositories(String username) {
        return repository.getSearchResultsFromMemory(username).switchIfEmpty(repository.getSearchResultsFromNetwork(username));
    }

}
