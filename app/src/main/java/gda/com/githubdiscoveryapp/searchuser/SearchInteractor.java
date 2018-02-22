package gda.com.githubdiscoveryapp.searchuser;

/**
 * Created by sundayakinsete on 22/02/2018.
 */

public interface SearchInteractor {

    interface OnAddressSearchListener {

        void addressNotFound();

        void onAddressFound(String address);
    }


    void geogeoCodeCoordinate(long latitude,long longitude);
}
