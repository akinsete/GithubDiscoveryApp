package gda.com.githubdiscoveryapp.searchuser;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import gda.com.githubdiscoveryapp.App;
import gda.com.githubdiscoveryapp.R;

public class SearchActivity extends AppCompatActivity implements SearchActivityMVP.View {

    @BindView(R.id.search_text)  EditText searchText;
    @BindView(R.id.btn_search)  Button searchButton;
    @BindView(R.id.recyclerView)  RecyclerView recyclerView;


    @Inject
    SearchActivityMVP.Presenter presenter;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ((App) getApplication()).getComponent().inject(this);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.searchButtonClicked();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getPreviousSearch();
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
        progressDialog = ProgressDialog.show(this,null,"Searching...",false,false);
    }

    @Override
    public void hideSearchDialog() {
        if(progressDialog != null){
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }

    @Override
    public void showPreviousSearch(List<Search> previousSearch) {

    }
}
