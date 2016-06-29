package com.mirhoseini.westwing.view;


import com.mirhoseini.westwing.model.Campaign;

import java.util.List;

/**
 * Created by Mohsen on 29/06/16.
 */
public interface CampaignView {

    void showProgress();

    void hideProgress();

    void updateProgressMessage(String newMessage);

    void showRetryErrorMessage();

    void setCampaigns(List<Campaign> campaigns);

}
