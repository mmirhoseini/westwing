package com.mirhoseini.westwing.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mirhoseini.utils.Utils;
import com.mirhoseini.westwing.R;
import com.mirhoseini.westwing.di.ApplicationComponent;
import com.mirhoseini.westwing.di.MainModule;
import com.mirhoseini.westwing.model.Campaign;
import com.mirhoseini.westwing.presentation.MainPresenter;
import com.mirhoseini.westwing.view.MainView;
import com.mirhoseini.westwing.view.fragment.CampaignsListFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Mohsen on 29/06/16.
 */
public class MainActivity extends BaseActivity implements MainView, CampaignsListFragment.OnListFragmentInteractionListener {
    public static final String TAG_CAMPAIGNS_LIST_FRAGMENT = "campaigns_list_fragment";


    // injecting dependencies via Dagger
    @Inject
    Context context;
    @Inject
    MainPresenter presenter;
    @Inject
    Resources resources;

    // injecting views via Butterknife
    @BindView(R.id.fragment_container)
    ViewGroup fragmentContainer;

    AlertDialog internetConnectionDialog;
    private CampaignsListFragment campaignsListFragment;

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component
                .plus(new MainModule(this))
                .inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inject views using ButterKnife
        ButterKnife.bind(this);

        Timber.d("Main Activity Created");
    }

    @Override
    protected void onResume() {
        super.onResume();

        FragmentManager fragmentManager = getSupportFragmentManager();
        campaignsListFragment = (CampaignsListFragment) fragmentManager.findFragmentByTag(TAG_CAMPAIGNS_LIST_FRAGMENT);

        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change.
        if (campaignsListFragment == null) {
            createFragments();
        }

        showFragments();

        presenter.onResume();

        Timber.d("Activity Resumed");
    }

    private void createFragments() {
        campaignsListFragment = CampaignsListFragment.newInstance(1);
        campaignsListFragment.setRetainInstance(true);
    }

    private void showFragments() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.campaigns_list_fragment, campaignsListFragment, TAG_CAMPAIGNS_LIST_FRAGMENT);
        fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    public void onBackPressed() {
        Timber.d("Activity Back Pressed");

        presenter.onBackPressed();
    }

    @Override
    public void showExitMessage() {
        Timber.d("Showing Exit Message");

        showMessage(resources.getString(R.string.msg_exit));
    }

    @Override
    public void showMessage(String message) {
        Timber.d("Showing Toast Message: %s", message);

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showInternetConnectionError() {
        Timber.d("Showing Connection Error Message");

        hideInternetConnectionError();

        internetConnectionDialog = Utils.showNoInternetConnectionDialog(this, true);
    }

    @Override
    public void hideInternetConnectionError() {
        if (internetConnectionDialog != null)
            internetConnectionDialog.dismiss();
    }

    @Override
    public void exit() {
        finish();
    }

    @Override
    public void onListFragmentInteraction(Campaign campaign) {
        Intent campaignIntent = CampaignActivity.newIntent(context, campaign);
        startActivity(campaignIntent);
    }
}