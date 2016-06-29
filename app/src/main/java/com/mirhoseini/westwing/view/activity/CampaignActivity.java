package com.mirhoseini.westwing.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mirhoseini.westwing.R;
import com.mirhoseini.westwing.di.ApplicationComponent;
import com.mirhoseini.westwing.model.Campaign;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by Mohsen on 29/06/16.
 */
public class CampaignActivity extends BaseActivity {

    public static final String EXTRA_CAMPAIGN_DATA = "campaign_data";

    @Inject
    Context context;
    @Inject
    Resources resources;

    // injecting views via Butterknife
    @BindView(R.id.banner)
    ImageView banner;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.sub_line)
    TextView subLine;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.navigation_url)
    TextView navigationUrl;

    private boolean isBannerLoaded = false;
    private boolean isBannerLoading = false;

    @OnClick(R.id.banner)
    void onBannerClick(View view) {
        if (!isBannerLoaded && !isBannerLoading)
            loadBanner();
    }

    Campaign campaign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);

        campaign = getIntent().getExtras().getParcelable(EXTRA_CAMPAIGN_DATA);

        if (campaign == null)
            finish();

        // inject views using ButterKnife
        ButterKnife.bind(this);

        // enable actionbar back button
        getSupportActionBar().setHomeButtonEnabled(true);

        Timber.d("Campaign Activity Created");

        loadView();
    }

    private void loadView() {
        loadBanner();
        name.setText(campaign.getName());
        time.setText(campaign.getStartTimeFormatted());
        subLine.setText(campaign.getSubline());
        description.setText(campaign.getDescription());
        navigationUrl.setText(campaign.getNavigationUrl());
    }

    private void loadBanner() {
        isBannerLoading = true;

        Picasso.with(context)
                .load(campaign.getImages().getBanner().getUrl())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.ic_no_internet)
                .into(banner, new Callback() {
                    @Override
                    public void onSuccess() {
                        isBannerLoaded = true;
                        isBannerLoading = false;
                    }

                    @Override
                    public void onError() {
                        isBannerLoaded = false;
                        isBannerLoading = false;
                    }
                });
    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component.inject(this);
    }

    public static Intent newIntent(Context context, Campaign campaign) {
        Intent intent = new Intent(context, CampaignActivity.class);
        intent.putExtra(CampaignActivity.EXTRA_CAMPAIGN_DATA, campaign);
        return intent;
    }
}
