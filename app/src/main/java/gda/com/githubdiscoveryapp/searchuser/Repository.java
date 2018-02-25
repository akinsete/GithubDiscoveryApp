package gda.com.githubdiscoveryapp.searchuser;

import java.util.List;

import gda.com.githubdiscoveryapp.data.models.Repo;
import gda.com.githubdiscoveryapp.data.models.Result;
import gda.com.githubdiscoveryapp.data.models.Search;
import rx.Observable;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

/**
 * Defines an interface to the service API that is used by this application to store data locally. All data request should
 * be piped through this interface.
 */
public interface Repository {

    Observable<List<Repo>> getSearchResultsFromMemory(String user);

    Observable<List<Repo>> getSearchResultsFromNetwork(String user);

    Observable<Result> getGeocodedAddressFromMemory(String location);

    Observable<Result> getGeocodedAddressFromNetwork(String location);

    List<Search> getPreviousSearches();

    void saveSearch(Search search);
}
