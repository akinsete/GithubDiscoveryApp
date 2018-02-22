package gda.com.githubdiscoveryapp.searchuser;

import android.support.annotation.Nullable;

import java.util.List;

import gda.com.githubdiscoveryapp.data.models.Search;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public class SearchActivityPresenter implements SearchActivityMVP.Presenter{

    @Nullable
    private SearchActivityMVP.View view;
    private SearchActivityMVP.Model model;


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
                model.saveSearch(view.getSearchText(),0,0,"");
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
}
