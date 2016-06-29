package com.mirhoseini.westwing.di;

import com.mirhoseini.westwing.view.fragment.CampaignsListFragment;

import dagger.Subcomponent;

/**
 * Created by Mohsen on 29/06/16.
 */
@CampaignScope
@Subcomponent(modules = {
        CampaignModule.class
})
public interface CampaignComponent {

    void inject(CampaignsListFragment fragment);

}
