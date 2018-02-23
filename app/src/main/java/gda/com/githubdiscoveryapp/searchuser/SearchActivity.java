package gda.com.githubdiscoveryapp.searchuser;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import gda.com.githubdiscoveryapp.di.App;
import gda.com.githubdiscoveryapp.R;
import gda.com.githubdiscoveryapp.data.models.Repo;
import gda.com.githubdiscoveryapp.data.models.Search;
import gda.com.githubdiscoveryapp.usersrepolist.UserRepoListActivity;


public class SearchActivity extends AppCompatActivity implements SearchActivityMVP.View,
        GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks,LocationListener{

    @BindView(R.id.search_text)  EditText searchText;
    @BindView(R.id.btn_search)  Button searchButton;
    @BindView(R.id.recyclerView)  RecyclerView recyclerView;

    @Inject
    SearchActivityMVP.Presenter presenter;

    ProgressDialog progressDialog;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    final int REQUEST_LOCATION_PERMISSION_CODE = 100;
    PreviousSearchAdapter previousSearchAdapter;
    List<Search> searches = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ((App) getApplication()).getComponent().inject(this);

        setupGoogleApiClient();

        setupRecyclerView();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.searchButtonClicked();
            }
        });
    }


    /**
     * Listener to capture clicked item from the previoussearch recyclerview
     */
    PreviousSearchAdapter.OnSearchItemClickListener searchItemListener = new PreviousSearchAdapter.OnSearchItemClickListener() {
        @Override
        public void onItemClick(Search search) {
            searchText.setText(search.getUsername());
            presenter.searchButtonClicked();
        }
    };


    private void setupRecyclerView() {
        previousSearchAdapter = new PreviousSearchAdapter(searches,searchItemListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(previousSearchAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getPreviousSearch();
    }



    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    private synchronized void setupGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this,new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_LOCATION_PERMISSION_CODE);
            return;
        }
        startRequestLocationUpdate();
    }


    @SuppressLint("MissingPermission")
    private void startRequestLocationUpdate() {
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mLocation != null){
            presenter.setUserLocation(String.valueOf(mLocation.getLatitude()),String.valueOf(mLocation.getLongitude()));
        }else{
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setInterval(1000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest,this);
        }


    }

    @Override
    public String getSearchText() {
        return searchText.getText().toString();
    }

    @Override
    public void showNoPreviousSearch() {
        Toast.makeText(this,"No previous searched username available",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this,"Enter github username",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSearchSavedMessage() {
        Toast.makeText(this,"Search saved",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSearchDialog() {
        progressDialog = ProgressDialog.show(this,null,"Searching...",true,true);
    }

    @Override
    public void hideSearchDialog() {
        if(progressDialog != null){
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }


    /**
     * Update previous search data
     * @param previousSearch
     */
    @Override
    public void showPreviousSearch(List<Search> previousSearch) {
        previousSearchAdapter.setItems(previousSearch);
        previousSearchAdapter.notifyDataSetChanged();
    }


    /**
     * Redirects the app to user's list of repos.
     * @param repos
     */
    @Override
    public void navigateToRepoListView(ArrayList<Repo> repos) {
        Intent intent = new Intent(this, UserRepoListActivity.class);
        intent.putExtra("repos",repos);
        startActivity(intent);
    }


    @Override
    public void showNoUserNameFound() {
        Toast.makeText(this,"User not found",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_LOCATION_PERMISSION_CODE:

                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startRequestLocationUpdate();
                }
                break;
        }
    }


    @Override
    public void onConnectionSuspended(int i) {
        if(mGoogleApiClient != null){
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLocation = location;
        if(mLocation != null){
            presenter.setUserLocation(String.valueOf(mLocation.getLatitude()),String.valueOf(mLocation.getLongitude()));
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);
        }
    }
}
