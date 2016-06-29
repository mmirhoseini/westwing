package com.mirhoseini.westwing.di;

import android.content.Context;

import com.mirhoseini.utils.Utils;
import com.mirhoseini.westwing.BuildConfig;
import com.mirhoseini.westwing.util.Constants;
import com.mirhoseini.westwing.util.SchedulerProvider;
import com.mirhoseini.westwing.util.AppSchedulerProvider;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import retrofit2.BaseUrl;

/**
 * Created by Mohsen on 29/06/16.
 */
@Module
public class ApplicationModule {
    @Provides
    @Singleton
    @Named("isDebug")
    public boolean provideIsDebug() {
        return BuildConfig.DEBUG;
    }

    @Provides
    @Singleton
    @Named("networkTimeoutInSeconds")
    public int provideNetworkTimeoutInSeconds() {
        return 30;
    }

    @Provides
    @Singleton
    public BaseUrl provideEndpoint() {
        return () -> HttpUrl.parse(Constants.BASE_URL);
    }

    @Provides
    @Singleton
    public SchedulerProvider provideAppScheduler() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    @Named("cacheSize")
    public long provideCacheSize() {
        return 10 * 1024 * 1024; // 10 MB
    }

    @Provides
    @Singleton
    @Named("cacheDir")
    public File provideCacheDir(Context context) {
        return context.getCacheDir();
    }

    @Provides
    @Named("isConnected")
    public boolean provideIsConnected(Context context) {
        return Utils.isConnected(context);
    }

}
