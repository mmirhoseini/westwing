package com.mirhoseini.westwing.service;

import com.mirhoseini.westwing.model.Campaign;
import com.mirhoseini.westwing.util.SchedulerProvider;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Mohsen on 29/06/16.
 */
public class CampaignInteractorTest {
    private CampaignInteractor campaignInteractor;
    private CampaignApi campaignApi;
    private SchedulerProvider schedulerProvider;
    private ArrayList<Campaign> expectedResult;

    @Before
    public void setup() {
        campaignApi = mock(CampaignApi.class);
        schedulerProvider = mock(SchedulerProvider.class);

        Campaign campaign = new Campaign("Name", "SubLine", "http://westwing.de", "Description", "Today", null);

        expectedResult = new ArrayList<>();
        expectedResult.add(campaign);

        when(schedulerProvider.mainThread()).thenReturn(Schedulers.immediate());
        when(schedulerProvider.backgroundThread()).thenReturn(Schedulers.immediate());

        when(campaignApi.getCampaignData()).thenReturn(Observable.just(expectedResult));

        campaignInteractor = new CampaignInteractorImpl(campaignApi, schedulerProvider);
    }


    @Test
    public void loadCampaignData() throws Exception {
        // must load data from Network, cause Memory and disk cache are null
        TestSubscriber<ArrayList<Campaign>> testSubscriber = new TestSubscriber<>();
        campaignInteractor.loadCampaignData().subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Collections.singletonList(expectedResult));
    }

}