package gda.com.githubdiscoveryapp.searchuser;

import java.util.List;

import gda.com.githubdiscoveryapp.data.models.Search;

/**
 * Created by sundayakinsete on 25/02/2018.
 */

public interface LocaldatabaseRepository {

    List<Search> getPreviousSearches();

    void saveSearch(Search search);
}
