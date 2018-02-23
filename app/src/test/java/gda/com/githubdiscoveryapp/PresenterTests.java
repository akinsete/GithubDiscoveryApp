package gda.com.githubdiscoveryapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import gda.com.githubdiscoveryapp.data.models.Search;
import gda.com.githubdiscoveryapp.searchuser.SearchActivityMVP;
import gda.com.githubdiscoveryapp.searchuser.SearchActivityPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;


/**
 * Created by sundayakinsete on 22/02/2018.
 */

public class PresenterTests {

    SearchActivityMVP.Model mockSearchModel;
    SearchActivityMVP.View mockSearchView;
    SearchActivityPresenter presenter;

    List<Search> previousSearch = new ArrayList<>();
    Search search = new Search("Sunday","-36","-47","Abaranje");




    @Before
    public void setup(){
        mockSearchModel = mock(SearchActivityMVP.Model.class);


//        previousSearch.add(search);
//
//        //when(mockSearchModel.previousSearches()).thenReturn(previousSearch);

        mockSearchView = mock(SearchActivityMVP.View.class);

        presenter = new SearchActivityPresenter(mockSearchModel);

        presenter.setView(mockSearchView);

    }


    @Test
    public void confirmShowPreviousSearchWasCalledWhenDataIsAvailable(){
        previousSearch.add(search);

        when(mockSearchModel.previousSearches()).thenReturn(previousSearch);

        presenter.getPreviousSearch();

        verify(mockSearchModel,times(1)).previousSearches();

        verify(mockSearchView,times(1)).showPreviousSearch(previousSearch);

        verify(mockSearchView,never()).showNoPreviousSearch();
    }


    @Test
    public void shouldShowToastMessageWhenPreviousSearchListIsNull(){
        when(mockSearchModel.previousSearches()).thenReturn(null);

        presenter.getPreviousSearch();

        //// Verify Model interaction /////
        verify(mockSearchModel,times(1)).previousSearches();

        //// Verify view interactions ///////
        //// verify when previous message is null it never calls showPreviousSearch ////
        verify(mockSearchView,never()).showPreviousSearch(previousSearch);

        /// Ensure the error message is shown at least once if previous search repository is empty
        verify(mockSearchView,times(1)).showNoPreviousSearch();
        //verifyZeroInteractions(mockSearchView);
    }



    @Test
    public void shouldCreateErrorMessageIfSearchFieldIsEmpty(){
        //////  Setup the mock view
        when(mockSearchView.getSearchText()).thenReturn("");

        presenter.searchButtonClicked();

        verify(mockSearchView,times(1)).showInputError();

        verify(mockSearchView,never()).showSearchDialog();
    }



    @Test
    public void shouldBeAbleToSaveAValidSearchedUsername(){
        when(mockSearchView.getSearchText()).thenReturn("Akinsete");

        presenter.searchButtonClicked();

        ////// Verify search test was retrieved from the edit test
        verify(mockSearchView,times(2)).getSearchText();

        ////// Verify the search dialog was shown ///////
        verify(mockSearchView,times(1)).showSearchDialog();

        ///// Verify the model also tried saving the data
        verify(mockSearchModel,times(1)).saveSearch("Akinsete","0","0","");
    }



    @Test
    public void ensureSearchedTextRemainThesameWhenSaved(){
        when(mockSearchView.getSearchText()).thenReturn("Sunday");

        presenter.searchButtonClicked();

        verify(mockSearchModel,never()).saveSearch("Akinsete","0","0","");
    }



}
