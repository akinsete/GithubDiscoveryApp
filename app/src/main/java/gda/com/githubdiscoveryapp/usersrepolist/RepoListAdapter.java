package gda.com.githubdiscoveryapp.usersrepolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gda.com.githubdiscoveryapp.R;
import gda.com.githubdiscoveryapp.data.models.Repo;

/**
 * Created by sundayakinsete on 23/02/2018.
 */

public class RepoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Repo> repos = new ArrayList<>();
    private OnRepoItemClickListener onRepoItemClickListener;


    public RepoListAdapter(List<Repo> repos, OnRepoItemClickListener onRepoItemClickListener) {
        this.repos = repos;
        this.onRepoItemClickListener = onRepoItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_adapter_row_item,parent,false);


        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RepoViewHolder repoViewHolder = (RepoViewHolder) holder;

        repoViewHolder.bindData(repos.get(position));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }


    public class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View view;
        Repo repo;
        @BindView(R.id.owner_avatar) ImageView owner_avatar;
        @BindView(R.id.repo_title) TextView repo_title;
        @BindView(R.id.repo_watchers) TextView repo_watchers;
        @BindView(R.id.repo_language) TextView repo_language;
        @BindView(R.id.repo_description) TextView repo_description;

        private RepoViewHolder(View itemView) {
            super(itemView);

            view = itemView;

            ButterKnife.bind(this,view);

            view.setOnClickListener(this);
        }

        void bindData(Repo repo){
            this.repo = repo;
            Picasso.with(view.getContext())
                    .load(repo.getOwner().getAvatarUrl())
                    .resize(130, 130)
                    .centerCrop()
                    .into(owner_avatar);
            repo_title.setText(repo.getName());
            repo_description.setText(repo.getDescription());
            if(repo.getLanguage() != null){
                repo_language.setText(repo.getLanguage().toString());
            }
            repo_watchers.setText(String.valueOf(repo.getOpenIssuesCount()));
        }

        @Override
        public void onClick(View v) {
            onRepoItemClickListener.onItemClick(repo);
        }
    }


    interface OnRepoItemClickListener{

        void onItemClick(Repo repo);
    }
}
