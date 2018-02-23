package gda.com.githubdiscoveryapp.usersrepolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import gda.com.githubdiscoveryapp.di.App;
import gda.com.githubdiscoveryapp.R;
import gda.com.githubdiscoveryapp.data.models.Repo;
import gda.com.githubdiscoveryapp.repodetails.RepoDetailActivity;

public class UserRepoListActivity extends AppCompatActivity implements UserRepoListActivityMVP.View {


    List<Repo> repos = new ArrayList<>();

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Inject
    UserRepoListActivityMVP.Presenter presenter;

    RepoListAdapter repoListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_repo_list);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repos = (List<Repo>) getIntent().getExtras().getSerializable("repos");

        setupRecyclerView();

        ((App) getApplication()).getComponent().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    /**
     * Listener to get clicked item from the repo recyclerview
     */
    RepoListAdapter.OnRepoItemClickListener onRepoItemClickListener = new RepoListAdapter.OnRepoItemClickListener() {
        @Override
        public void onItemClick(Repo repo) {
            presenter.navigateToDetailView(repo);
        }
    };


    /**
     * Setup RecyclerView
     */
    private void setupRecyclerView() {
        repoListAdapter = new RepoListAdapter(repos,onRepoItemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(repoListAdapter);
    }


    @Override
    public void gotoDetailView(Repo repo) {
        Intent intent = new Intent(this,RepoDetailActivity.class);
        intent.putExtra("repo",repo);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
