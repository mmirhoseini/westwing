package com.mirhoseini.westwing.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.mirhoseini.westwing.WestwingApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 29/06/16.
 */
@Module
public class AndroidModule {
    private WestwingApplication westwingApplication;

    public AndroidModule(WestwingApplication westwingApplication) {
        this.westwingApplication = westwingApplication;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return westwingApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    public Resources provideResources() {
        return westwingApplication.getResources();
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(westwingApplication);
    }

}
