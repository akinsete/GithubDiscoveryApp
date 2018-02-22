package gda.com.githubdiscoveryapp.searchuser;

import java.util.List;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

/**
 * Defines an interface to the service API that is used by this application to store data locally. All data request should
 * be piped through this interface.
 */
public interface SearchRepository {

    List<Search> getPreviousSearches();

    void saveSearch(Search search);

}
