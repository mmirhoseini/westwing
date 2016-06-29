package com.mirhoseini.westwing.service;



import com.mirhoseini.westwing.model.Campaign;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Mohsen on 29/06/16.
 */
public interface CampaignApi {

    // http://static.westwing.de/cms/dont-delete/programming_task/data.json
    @GET("data.json")
    Observable<List<Campaign>> getCampaignData();

}
