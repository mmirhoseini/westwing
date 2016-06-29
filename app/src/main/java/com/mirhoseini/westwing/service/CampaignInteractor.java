package com.mirhoseini.westwing.service;


import com.mirhoseini.westwing.model.Campaign;

import java.util.List;

import rx.Observable;

/**
 * Created by Mohsen on 29/06/16.
 */
public interface CampaignInteractor {

    Observable<List<Campaign>> loadCampaignData();

}