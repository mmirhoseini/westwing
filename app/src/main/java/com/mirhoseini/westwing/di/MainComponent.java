package com.mirhoseini.westwing.di;

import com.mirhoseini.westwing.view.activity.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Mohsen on 29/06/16.
 */
@CampaignScope
@Subcomponent(modules = {
        MainModule.class
})
public interface MainComponent {

    void inject(MainActivity activity);

}
