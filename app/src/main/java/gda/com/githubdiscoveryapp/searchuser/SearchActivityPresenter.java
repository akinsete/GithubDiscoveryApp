package gda.com.githubdiscoveryapp.searchuser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.List;

import javax.inject.Inject;

import gda.com.githubdiscoveryapp.data.models.Search;
import gda.com.githubdiscoveryapp.location.LocationCallbacks;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public class SearchActivityPresenter implements SearchActivityMVP.Presenter{

    @Nullable
    private SearchActivityMVP.View view;
    private SearchActivityMVP.Model model;

    private long latitude;
    private long longitude;
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
                model.saveSearch(view.getSearchText(),latitude,longitude,locationAddress);


                view.showSearchSavedMessage();
            }
        }
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

        model.getAddressOfLocation(latitude, longitude, new ReverseGeocodeCallback() {
            @Override
            public void address(String string) {

                locationAddress = string;

                Log.e("address",locationAddress);

            }
        });
    }


}
