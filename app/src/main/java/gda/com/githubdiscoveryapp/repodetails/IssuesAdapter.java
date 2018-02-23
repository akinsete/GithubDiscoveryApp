package gda.com.githubdiscoveryapp.repodetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gda.com.githubdiscoveryapp.R;
import gda.com.githubdiscoveryapp.data.models.Issue;

/**
 * Created by sundayakinsete on 23/02/2018.
 */

public class IssuesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Issue> issues = new ArrayList<>();

    public IssuesAdapter(List<Issue> issues) {
        this.issues = issues;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.issue_adapter_row_item,parent,false);

        return new IssueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IssueViewHolder issueViewHolder = (IssueViewHolder) holder;
        issueViewHolder.bindData(issues.get(position));
    }

    @Override
    public int getItemCount() {
        return issues.size();
    }

    public void setData(List<Issue> data) {
        this.issues = data;
    }


    public class IssueViewHolder extends RecyclerView.ViewHolder{
        View view;
        @BindView(R.id.issue_name) TextView issue_name;
        @BindView(R.id.username) TextView username;
        @BindView(R.id.created_date) TextView created_date;
        @BindView(R.id.issue_body) TextView issue_body;

        public IssueViewHolder(View itemView) {
            super(itemView);

            view = itemView;

            ButterKnife.bind(this,itemView);
        }


        void bindData(Issue issue){
            issue_name.setText(issue.getTitle());
            username.setText(issue.getUser().getLogin());
            issue_body.setText(issue.getBody());
            created_date.setText(issue.getCreatedAt());
        }
    }
}
