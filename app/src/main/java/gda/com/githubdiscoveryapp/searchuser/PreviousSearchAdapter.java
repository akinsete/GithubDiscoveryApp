package gda.com.githubdiscoveryapp.searchuser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.marlonlom.utilities.timeago.TimeAgo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gda.com.githubdiscoveryapp.R;
import gda.com.githubdiscoveryapp.data.models.Search;

/**
 * Created by sundayakinsete on 22/02/2018.
 */

public class PreviousSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Search> searches = new ArrayList<>();
    OnSearchItemClickListener searchItemListener;

    public PreviousSearchAdapter(List<Search> searches, OnSearchItemClickListener searchItemListener) {
        this.searches = searches;
        this.searchItemListener = searchItemListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.previous_search_item,parent,false);


        return new SearchItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchItemViewHolder viewHolder = (SearchItemViewHolder) holder;
        viewHolder.bindData(searches.get(position));
    }


    @Override
    public int getItemCount() {
        return searches.size();
    }

    public void setItems(List<Search> items) {
        this.searches = items;
    }


    public class SearchItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View view;
        Search search;
        @BindView(R.id.txt_address) TextView txt_address;
        @BindView(R.id.txt_user_name) TextView txt_user_name;
        @BindView(R.id.txt_time) TextView txt_time;

        public SearchItemViewHolder(View itemView) {
            super(itemView);

            view = itemView;

            ButterKnife.bind(this,view);

            itemView.setOnClickListener(this);
        }

        void bindData(Search search){
            this.search = search;
            txt_address.setText(search.getAddress());
            txt_user_name.setText(search.getUsername());
            txt_time.setText(TimeAgo.using(search.getDate()));
            //Log.e("date",String.valueOf(search.getDate()));
        }

        @Override
        public void onClick(View v) {
            searchItemListener.onItemClick(search);
        }
    }

    public interface OnSearchItemClickListener {

        void onItemClick(Search search);

    }
}
