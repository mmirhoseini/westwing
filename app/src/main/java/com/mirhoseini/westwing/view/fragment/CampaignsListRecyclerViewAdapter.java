package com.mirhoseini.westwing.view.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mirhoseini.westwing.R;
import com.mirhoseini.westwing.model.Campaign;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohsen on 29/06/16.
 */
public class CampaignsListRecyclerViewAdapter extends RecyclerView.Adapter<CampaignsListRecyclerViewAdapter.ViewHolder> {

    private final List<Campaign> campaigns;
    private final CampaignsListFragment.OnListFragmentInteractionListener listener;

    public CampaignsListRecyclerViewAdapter(List<Campaign> campaigns, CampaignsListFragment.OnListFragmentInteractionListener listener) {
        this.campaigns = campaigns;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_campaign, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.campaign = campaigns.get(position);
        holder.name.setText(campaigns.get(position).getName());
        holder.subLine.setText(campaigns.get(position).getSubline());
        holder.navigationUrl.setText(campaigns.get(position).getNavigationUrl());

        holder.view.setOnClickListener(v -> {
            if (null != listener) {
                listener.onListFragmentInteraction(holder.campaign);
            }
        });
    }

    @Override
    public int getItemCount() {
        return campaigns.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.sub_line)
        TextView subLine;
        @BindView(R.id.navigation_url)
        TextView navigationUrl;

        public Campaign campaign;

        public ViewHolder(View view) {
            super(view);
            this.view = view;

            ButterKnife.bind(this, view);
        }

    }
}
