package com.mirhoseini.westwing.presentation;


import com.mirhoseini.westwing.view.MainView;

/**
 * Created by Mohsen on 29/06/16.
 */
public interface MainPresenter {

    void setView(MainView view);

    void onResume();

    void onBackPressed();
}
