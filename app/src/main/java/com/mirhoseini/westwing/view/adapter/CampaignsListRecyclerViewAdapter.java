package com.mirhoseini.westwing.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mirhoseini.westwing.R;
import com.mirhoseini.westwing.model.Campaign;
import com.mirhoseini.westwing.view.fragment.CampaignsListFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;

/**
 * Created by Mohsen on 29/06/16.
 */
public class CampaignsListRecyclerViewAdapter extends RecyclerView.Adapter<CampaignsListRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private final ArrayList<Campaign> campaigns;
    private final CampaignsListFragment.OnListFragmentInteractionListener listener;

    public CampaignsListRecyclerViewAdapter(Context context, ArrayList<Campaign> campaigns, CampaignsListFragment.OnListFragmentInteractionListener listener) {
        this.context = context;
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
        holder.loadImage(campaigns.get(position).getNavigationUrl());

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
        public Campaign campaign;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.sub_line)
        TextView subLine;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.progress)
        ProgressBar progress;

        @OnTouch(R.id.image)
        boolean onImageClick(View view, MotionEvent motionEvent) {
            if (!isBannerLoaded && !isBannerLoading) {
                loadImage(campaign.getNavigationUrl());
                return true;
            } else {
                // continue with list item click
                return false;
            }
        }

        private boolean isBannerLoaded = false;
        private boolean isBannerLoading = false;

        public ViewHolder(View view) {
            super(view);
            this.view = view;

            ButterKnife.bind(this, view);

        }

        private void loadImage(String navigationUrl) {
            isBannerLoading = true;
            progress.setVisibility(View.VISIBLE);

            Picasso.with(context)
                    .load(navigationUrl)
                    .error(R.drawable.ic_no_internet)
                    .into(image, new Callback() {
                        @Override
                        public void onSuccess() {
                            progress.setVisibility(View.GONE);
                            isBannerLoaded = true;
                            isBannerLoading = false;
                        }

                        @Override
                        public void onError() {
                            progress.setVisibility(View.GONE);
                            isBannerLoaded = false;
                            isBannerLoading = false;
                        }
                    });
        }

    }
}
