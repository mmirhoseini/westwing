package com.mirhoseini.westwing.view;


import com.mirhoseini.westwing.model.Campaign;

import java.util.ArrayList;

/**
 * Created by Mohsen on 29/06/16.
 */
public interface CampaignView {

    void showOfflineMessage();

    void showProgress();

    void hideProgress();

    void updateProgressMessage(String newMessage);

    void showRetryErrorMessage();

    void setCampaigns(ArrayList<Campaign> campaigns);

}
