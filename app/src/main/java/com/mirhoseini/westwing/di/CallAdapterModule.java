package com.mirhoseini.westwing.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Mohsen on 29/06/16.
 */
@Module
public class CallAdapterModule {
    @Provides
    @Singleton
    public CallAdapter.Factory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }
}
