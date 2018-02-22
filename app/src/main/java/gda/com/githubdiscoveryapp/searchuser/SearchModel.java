package gda.com.githubdiscoveryapp.searchuser;

import android.util.Log;

import java.util.List;

import gda.com.githubdiscoveryapp.data.models.Repo;
import gda.com.githubdiscoveryapp.data.models.Search;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public class SearchModel implements SearchActivityMVP.Model {

    SearchRepository repository;

    public SearchModel(SearchRepository repository) {
        this.repository = repository;
    }


    @Override
    public void getAddressOfLocation(long latitude, long longitude, ReverseGeocodeCallback reverseGeocodeCallback) {
        Log.e("Tag",String.valueOf(latitude));
        Log.e("Tag",String.valueOf(longitude));
        reverseGeocodeCallback.address("25 Shomolu Street");
    }

    @Override
    public void saveSearch(String username, long latitude, long longitude, String address) {
        repository.saveSearch(new Search(username,latitude,longitude,address));
    }

    @Override
    public List<Search> previousSearches() {
        return repository.getPreviousSearches();
    }

    @Override
    public List<Repo> getGithubRepositories(String username) {
        return null;
    }
}
