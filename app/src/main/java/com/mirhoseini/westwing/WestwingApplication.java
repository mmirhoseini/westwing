package com.mirhoseini.westwing;

import android.app.Application;

import com.mirhoseini.westwing.di.AndroidModule;
import com.mirhoseini.westwing.di.ApplicationComponent;
import com.mirhoseini.westwing.di.DaggerApplicationComponent;

/**
 * Created by Mohsen on 29/06/16.
 */
public abstract class WestwingApplication extends Application {
    private static ApplicationComponent component;

    public static ApplicationComponent getComponent() {
        return component;
    }

    protected AndroidModule getAndroidModule() {
        return new AndroidModule(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initApplication();

        component = DaggerApplicationComponent.builder()
                .androidModule(getAndroidModule())
                .build();
    }

    abstract void initApplication();
}
