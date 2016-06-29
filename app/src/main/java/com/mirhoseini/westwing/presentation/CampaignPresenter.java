package com.mirhoseini.westwing.presentation;


import com.mirhoseini.westwing.view.CampaignView;
import com.mirhoseini.westwing.view.MainView;

/**
 * Created by Mohsen on 29/06/16.
 */
public interface CampaignPresenter {

    void setView(CampaignView view);

    void setParentView(MainView parentView);

    void loadData(boolean isConnected);

    void destroy();

}
