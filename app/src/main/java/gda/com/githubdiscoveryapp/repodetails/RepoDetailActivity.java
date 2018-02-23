package gda.com.githubdiscoveryapp.repodetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import gda.com.githubdiscoveryapp.R;
import gda.com.githubdiscoveryapp.data.models.Issue;
import gda.com.githubdiscoveryapp.data.models.Repo;
import gda.com.githubdiscoveryapp.di.App;

public class RepoDetailActivity extends AppCompatActivity implements RepoDetailActivityMVP.View{


    @Inject
    RepoDetailActivityMVP.Presenter presenter;

    @BindView(R.id.repo_title) TextView repo_title;
    @BindView(R.id.repo_description) TextView repo_description;
    @BindView(R.id.repo_watchers) TextView repo_watchers;
    @BindView(R.id.repo_stars) TextView repo_stars;
    @BindView(R.id.repo_forks) TextView repo_forks;
    @BindView(R.id.repo_open_issues) TextView repo_open_issues;
    @BindView(R.id.repo_language) TextView repo_language;
    @BindView(R.id.owner_avatar) ImageView owner_avatar;

    Repo repo;
    List<Issue> issues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);

        ButterKnife.bind(this);

        ((App) getApplication()).getComponent().inject(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        repo = (Repo) getIntent().getExtras().getSerializable("repo");

        if(repo != null){
            displayRepoDetails(repo);
        }
    }

    private void displayRepoDetails(Repo repo) {
        repo_title.setText(repo.getName());
        repo_description.setText(repo.getDescription());

        if(repo.getLanguage() != null) {
            repo_language.setText(repo.getLanguage().toString());
        }

        repo_watchers.setText(String.valueOf(repo.getWatchers()));
        repo_forks.setText(String.valueOf(repo.getForksCount()));
        repo_stars.setText(String.valueOf(repo.getForks()));
        repo_open_issues.setText(String.valueOf(repo.getOpenIssuesCount()));

        Picasso.with(this)
                .load(repo.getOwner().getAvatarUrl())
                .resize(130, 130)
                .centerCrop()
                .into(owner_avatar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);

        if(repo != null){
            presenter.loadRepoIssues(repo.getFullName(),repo.getName());
        }
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

    @Override
    public void displayRepoIssues(List<Issue> issues) {
        this.issues = issues;


    }
}
