package com.mirhoseini.westwing.presentation;


import com.mirhoseini.westwing.di.CampaignScope;
import com.mirhoseini.westwing.service.CampaignInteractor;
import com.mirhoseini.westwing.view.CampaignView;
import com.mirhoseini.westwing.view.MainView;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by Mohsen on 29/06/16.
 */
@CampaignScope
public class CampaignPresenterImpl implements CampaignPresenter {

    CampaignInteractor interactor;
    private CampaignView view;
    private MainView parentView;
    private Subscription subscription = Subscriptions.empty();

    @Inject
    public CampaignPresenterImpl(CampaignInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void setView(CampaignView view) {
        this.view = view;
    }

    @Override
    public void setParentView(MainView parentView) {
        this.parentView = parentView;
    }

    @Override
    public void loadData(boolean isConnected) {

        if (view != null) {
            view.showProgress();
            view.updateProgressMessage("Loading Campaigns Data...");
        }

        subscription = interactor.loadCampaignData().subscribe(campaigns ->
                {
                    if (view != null) {
                        view.hideProgress();
                        view.setCampaigns(campaigns);
                    }
                },
                throwable -> {
                    if (view != null) {
                        view.hideProgress();
                    }

                    if (isConnected) {
                        if (view != null) {
                            view.showRetryErrorMessage();
                        }
                    } else {
                        if (parentView != null) {
                            parentView.showInternetConnectionError();
                        }
                    }
                });

    }

    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();

        view = null;
        parentView=null;
        interactor = null;
    }


}
