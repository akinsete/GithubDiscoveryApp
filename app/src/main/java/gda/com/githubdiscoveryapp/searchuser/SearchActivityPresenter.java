package gda.com.githubdiscoveryapp.searchuser;


import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import gda.com.githubdiscoveryapp.data.geocoder.GeocoderService;
import gda.com.githubdiscoveryapp.data.github.GithubService;
import gda.com.githubdiscoveryapp.data.models.Repo;
import gda.com.githubdiscoveryapp.data.models.Search;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public class SearchActivityPresenter implements SearchActivityMVP.Presenter{

    @Nullable
    private SearchActivityMVP.View view;
    private SearchActivityMVP.Model model;

    private long latitude = 0;
    private long longitude = 0;
    private String locationAddress = "";

    ///// Constructor injector ///
    public SearchActivityPresenter(SearchActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(SearchActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void searchButtonClicked() {
        if(view != null){
            if(view.getSearchText().isEmpty()){
                view.showInputError();
            }else{
                view.showSearchDialog();

                String username = view.getSearchText();

                proceedToSearch(username);

                model.saveSearch(username,latitude,longitude,locationAddress);

                getPreviousSearch();

                view.showSearchSavedMessage();
            }
        }
    }

    private void proceedToSearch(String username) {
        model.getGithubRepositories(username, new GithubService.getUserRepoListCallBack() {
            @Override
            public void onSuccess(List<Repo> repos) {
                if(view != null){

                    if(repos.size() > 0){
                        view.hideSearchDialog();

                        view.navigateToRepoListView((ArrayList<Repo>) repos);
                    }else{
                        view.showNoUserNameFound();
                    }

                }
            }

            @Override
            public void onError() {
                if(view != null){
                    view.showNoUserNameFound();
                    view.hideSearchDialog();
                }
            }
        });
    }

    @Override
    public void getPreviousSearch() {

        List<Search> previousSearches = model.previousSearches();

        if(previousSearches == null || previousSearches.size() == 0) {
            if(view != null) {
                view.showNoPreviousSearch();
            }
        }else{
            if(view != null) {
                view.showPreviousSearch(previousSearches);
            }
        }
    }

    @Override
    public void setUserLocation(long latitude, long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

        this.model.getReverseGeocodedAddress(String.valueOf(latitude)+" "+String.valueOf(longitude), new GeocoderService.GetFormattedAddressCallback() {
            @Override
            public void onSuccess(String address) {
                locationAddress = address;
            }
        });
    }


}
