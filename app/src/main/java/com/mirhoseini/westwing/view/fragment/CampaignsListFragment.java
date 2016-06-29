package com.mirhoseini.westwing.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mirhoseini.utils.Utils;
import com.mirhoseini.westwing.R;
import com.mirhoseini.westwing.di.ApplicationComponent;
import com.mirhoseini.westwing.di.CampaignModule;
import com.mirhoseini.westwing.model.Campaign;
import com.mirhoseini.westwing.presentation.CampaignPresenter;
import com.mirhoseini.westwing.view.CampaignView;
import com.mirhoseini.westwing.view.MainView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by Mohsen on 29/06/16.
 */
public class CampaignsListFragment extends BaseFragment implements CampaignView {

    private static final String ARG_COLUMN_COUNT = "column-count";


    //injecting dependencies via Dagger
    @Inject
    Context context;
    @Inject
    CampaignPresenter presenter;
    @Inject
    Resources resources;

    @BindView(R.id.progress_container)
    ViewGroup progressContainer;
    @BindView(R.id.progress_message)
    TextView progressMessage;
    @BindView(R.id.error_container)
    ViewGroup errorContainer;
    @BindView(R.id.list_container)
    ViewGroup listContainer;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CampaignsListRecyclerViewAdapter adapter;
    private ArrayList<Campaign> campaigns;

    @OnClick(R.id.error_container)
    void retryNetworkError(View view) {
        loadCampaignsData();
    }


    private int columnCount = 1;
    private OnListFragmentInteractionListener listener;
    private MainView parentView;

    public CampaignsListFragment() {
    }

    public static CampaignsListFragment newInstance(int columnCount) {
        CampaignsListFragment fragment = new CampaignsListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            columnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        Timber.d("Fragment Created");
    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component
                .plus(new CampaignModule(parentView, this))
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campaigns_list, container, false);

        // inject views using ButterKnife
        ButterKnife.bind(this, view);

        // reload list state on orientation change
        if (isCampaignDataLoaded()) {
            loadCampaignDataToAdapter();
        }

        swipeRefresh.setOnRefreshListener(() -> loadCampaignsData());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!isCampaignDataLoaded()) {
            loadCampaignsData();
        }

    }

    private boolean isCampaignDataLoaded() {
        return adapter != null && adapter.getItemCount() > 0;
    }

    private void loadCampaignsData() {
        swipeRefresh.setRefreshing(true);

        presenter.loadData(Utils.isConnected(context));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            listener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }

        if (context instanceof MainView) {
            parentView = (MainView) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainView");
        }
    }

    @Override
    public void showOfflineMessage() {
        Timber.d("Showing Offline Message");

        Snackbar.make(getView(), R.string.offline_message, Snackbar.LENGTH_LONG)
                .setAction(R.string.go_online, v -> {
                    startActivity(new Intent(
                            Settings.ACTION_WIFI_SETTINGS));
                })
                .setActionTextColor(Color.GREEN)
                .show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
        presenter = null;

        Timber.d("Fragment Detached");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.destroy();
    }

    @Override
    public void showProgress() {
        Timber.d("Showing Progress");

        progressContainer.setVisibility(View.VISIBLE);
        listContainer.setVisibility(View.INVISIBLE);
        errorContainer.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        Timber.d("Hiding Progress");

        progressContainer.setVisibility(View.INVISIBLE);

        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void updateProgressMessage(String newMessage) {
        Timber.d("Showing Progress Message: %s", newMessage);

        progressMessage.setText(newMessage);
    }

    @Override
    public void showRetryErrorMessage() {
        Timber.d("Showing Retry Message");

        errorContainer.setVisibility(View.VISIBLE);

        Snackbar.make(getView(), R.string.retry_message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.load_retry, v -> presenter.loadData(Utils.isConnected(context)))
                .setActionTextColor(Color.RED)
                .show();
    }

    @Override
    public void setCampaigns(ArrayList<Campaign> campaigns) {
        this.campaigns = campaigns;
        loadCampaignDataToAdapter();
    }

    private void loadCampaignDataToAdapter() {
        listContainer.setVisibility(View.VISIBLE);

        if (columnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        }

        adapter = new CampaignsListRecyclerViewAdapter(campaigns, listener);
        recyclerView.setAdapter(adapter);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Campaign campaign);
    }
}
