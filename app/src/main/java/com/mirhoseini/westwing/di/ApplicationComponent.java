package com.mirhoseini.westwing.di;

import com.mirhoseini.westwing.view.activity.CampaignActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mohsen on 29/06/16.
 */
@Singleton
@Component(modules = {
        AndroidModule.class,
        ApplicationModule.class,
        ApiModule.class,
        ClientModule.class
})
public interface ApplicationComponent {

    MainComponent plus(MainModule module);

    CampaignComponent plus(CampaignModule module);

    void inject(CampaignActivity campaignActivity);
}