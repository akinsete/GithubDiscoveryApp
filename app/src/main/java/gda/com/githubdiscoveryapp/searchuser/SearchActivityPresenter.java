package gda.com.githubdiscoveryapp.searchuser;


import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gda.com.githubdiscoveryapp.data.geocoder.GeocoderService;
import gda.com.githubdiscoveryapp.data.models.Repo;
import gda.com.githubdiscoveryapp.data.models.Result;
import gda.com.githubdiscoveryapp.data.models.Search;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public class SearchActivityPresenter implements SearchActivityMVP.Presenter{

    private static final String TAG = "searchPresenter";

    @Nullable
    private SearchActivityMVP.View view;
    private SearchActivityMVP.Model model;

    private String latitude = "0";
    private String longitude = "0";
    private String locationAddress = "";

    private Subscription subscription = null;

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

    /**
     * Start an RxJava subscription to get users repo list
     * @param username
     */
    private void proceedToSearch(String username) {
        subscription = model
                .getUserGithubRepositories(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(view != null){
                            view.showNoUserNameFound();
                            view.hideSearchDialog();
                        }
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        if(view != null){

                            if(repos.size() > 0){
                                view.hideSearchDialog();

                                view.navigateToRepoListView((ArrayList<Repo>) repos);
                            }else{
                                view.showNoUserNameFound();
                            }
                        }
                    }
                });
    }

    /**
     * Get previous searched username
     */
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


    /**
     * Store's users current location and run reverse geocode on the co-ordinate to get address.
     * @param latitude
     * @param longitude
     */
    @Override
    public void setUserLocation(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

        subscription = model.getReversedGeocodedAddress(String.valueOf(latitude)+" "+String.valueOf(longitude))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Result>() {
                    @Override
                    public void call(Result result) {
                        if(result != null) {
                            locationAddress = result.getFormattedAddress();
                        }

                        Log.e(TAG,String.valueOf(locationAddress));
                    }
                });
    }


    /**
     * On stop of the activity subscription should be removed,
     */
    @Override
    public void rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }


}
