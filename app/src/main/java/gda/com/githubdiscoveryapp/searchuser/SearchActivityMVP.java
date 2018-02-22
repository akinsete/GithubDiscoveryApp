package gda.com.githubdiscoveryapp.searchuser;

import java.util.List;

import gda.com.githubdiscoveryapp.data.models.Repo;
import gda.com.githubdiscoveryapp.data.models.Search;

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
    }

    interface Presenter{

        void setView(SearchActivityMVP.View view);

        void searchButtonClicked();

        void getPreviousSearch();

    }

    interface Model{

        void saveSearch(String username, long latitude, long longitude, String address);

        List<Search> previousSearches();

        List<Repo> getGithubRepositories(String username);
    }
}
