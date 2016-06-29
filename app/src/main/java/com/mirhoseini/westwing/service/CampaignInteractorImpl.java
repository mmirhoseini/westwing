package com.mirhoseini.westwing.service;


import com.mirhoseini.westwing.di.CampaignScope;
import com.mirhoseini.westwing.model.Campaign;
import com.mirhoseini.westwing.util.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.subjects.ReplaySubject;


/**
 * Created by Mohsen on 29/06/16.
 */
@CampaignScope
public class CampaignInteractorImpl implements CampaignInteractor {
    private CampaignApi campaignApi;
    private SchedulerProvider scheduler;

    private ReplaySubject<ArrayList<Campaign>> CampaignDataSubject;
    private Subscription campaignSubscription;

    @Inject
    public CampaignInteractorImpl(CampaignApi campaignApi, SchedulerProvider scheduler) {
        this.campaignApi = campaignApi;
        this.scheduler = scheduler;
    }


    @Override
    public Observable<ArrayList<Campaign>> loadCampaignData() {
        if (campaignSubscription == null || campaignSubscription.isUnsubscribed()) {
            CampaignDataSubject = ReplaySubject.create();

            campaignSubscription = campaignApi.getCampaignData()
                    .subscribeOn(scheduler.backgroundThread())
                    .observeOn(scheduler.mainThread())
                    .subscribe(CampaignDataSubject);
        }

        return CampaignDataSubject.asObservable();

    }

}
