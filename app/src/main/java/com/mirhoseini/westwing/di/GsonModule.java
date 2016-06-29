package com.mirhoseini.westwing.di;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 29/06/16.
 */
@Module
public class GsonModule {
    @Singleton
    @Provides
    public Gson provideGson() {
        return new Gson();
    }
}
