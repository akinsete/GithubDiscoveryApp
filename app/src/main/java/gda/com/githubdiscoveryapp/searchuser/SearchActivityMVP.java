package gda.com.githubdiscoveryapp.searchuser;

import java.util.ArrayList;
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

public interface SearchActivityMVP {

    interface View{

        String getSearchText();

        void showNoPreviousSearch();

        void showInputError();

        void showSearchSavedMessage();

        void showSearchDialog();

        void hideSearchDialog();

        void showPreviousSearch(List<Search> previousSearch);

        void navigateToRepoListView(ArrayList<Repo> repos);

        void showNoUserNameFound();
    }

    interface Presenter{

        void setView(SearchActivityMVP.View view);

        void searchButtonClicked();

        void getPreviousSearch();

        void setUserLocation(String latitude, String longitude);

        void rxUnsubscribe();
    }

    interface Model{

        void saveSearch(String username, String latitude, String longitude, String address);

        List<Search> previousSearches();

        Observable<Result> getReversedGeocodedAddress(String location);

        Observable<List<Repo>> getUserGithubRepositories(String username);

    }
}
