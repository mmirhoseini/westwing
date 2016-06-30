package com.mirhoseini.westwing.di;

import com.mirhoseini.westwing.presentation.CampaignPresenter;
import com.mirhoseini.westwing.presentation.CampaignPresenterImpl;
import com.mirhoseini.westwing.service.CampaignInteractor;
import com.mirhoseini.westwing.service.CampaignInteractorImpl;
import com.mirhoseini.westwing.view.CampaignView;
import com.mirhoseini.westwing.view.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 29/06/16.
 */
@Module
public class CampaignModule {
    private CampaignView view;
    private MainView parentView;

    public CampaignModule(MainView parentView, CampaignView view) {
        this.parentView = parentView;
        this.view = view;
    }

    @Provides
    @CampaignScope
    public CampaignView provideView() {
        return view;
    }

    @Provides
    @CampaignScope
    public CampaignPresenter providePresenter(CampaignPresenterImpl presenter) {
        presenter.setView(view);
        presenter.setParentView(parentView);
        return presenter;
    }

    @Provides
    @CampaignScope
    public CampaignInteractor provideInteractor(CampaignInteractorImpl interactor) {
        return interactor;
    }
}
