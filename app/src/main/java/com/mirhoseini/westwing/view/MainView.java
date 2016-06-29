package com.mirhoseini.westwing.view;

/**
 * Created by Mohsen on 29/06/16.
 */
public interface MainView {

    void showMessage(String message);

    void showInternetConnectionError();

    void hideInternetConnectionError();

    void exit();

    void showExitMessage();
}
