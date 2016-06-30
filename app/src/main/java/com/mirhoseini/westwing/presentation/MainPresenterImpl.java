package com.mirhoseini.westwing.presentation;


import com.mirhoseini.westwing.view.MainView;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Mohsen on 29/06/16.
 */
public class MainPresenterImpl implements MainPresenter {
    public static final int DOUBLE_BACK_PRESSED_DELAY = 2500;
    static boolean doubleBackToExitPressedOnce = false;

    private MainView view;

    @Inject
    public MainPresenterImpl() {

    }

    @Override
    public void setView(MainView view) {
        this.view = view;
    }

    @Override
    public void onResume() {
        doubleBackToExitPressedOnce = false;

        // dismiss no internet connection dialog in case of getting back from setting and connection fixed
        if (view != null)
            view.hideInternetConnectionError();
    }

    @Override
    public void onBackPressed() {
        // check for double back press to exit
        if (doubleBackToExitPressedOnce) {
            Timber.d("Exiting");

            if (view != null)
                view.exit();
        } else {

            doubleBackToExitPressedOnce = true;

            if (view != null)
                view.showExitMessage();

            Timer t = new Timer();
            t.schedule(new TimerTask() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }

            }, DOUBLE_BACK_PRESSED_DELAY);
        }
    }


}
