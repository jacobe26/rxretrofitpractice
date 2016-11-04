package com.elliottj.rxretrofitpractice.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elliottj.rxretrofitpractice.R;
import com.elliottj.rxretrofitpractice.service.model.GitHub;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elliottj on 11/3/16.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<GitHub> ghItems;

    public CardAdapter() {
        super();
        ghItems = new ArrayList<GitHub>();
    }

    public void addData(GitHub gitHub) {
        ghItems.add(gitHub);
        notifyDataSetChanged();
    }

    public void clear() {
        ghItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_card_view, parent, false);
        CardViewHolder vh = new CardViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        GitHub gh = ghItems.get(position);

        if (holder != null && gh != null) {
            holder.login.setText(gh.getLogin());
            holder.blog.setText("blog = " + gh.getBlog());
            holder.repos.setText("repos = " + gh.getPublicRepos());
        }
    }

    @Override
    public int getItemCount() {
        return ghItems.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {

        public TextView login;
        public TextView repos;
        public TextView blog;

        public CardViewHolder(View itemView) {
            super(itemView);
            login = (TextView) itemView.findViewById(R.id.login);
            repos = (TextView) itemView.findViewById(R.id.repos);
            blog = (TextView) itemView.findViewById(R.id.blog);
        }
    }
}
