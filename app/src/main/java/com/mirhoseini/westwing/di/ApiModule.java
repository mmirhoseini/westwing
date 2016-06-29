package com.mirhoseini.westwing.di;

import com.mirhoseini.westwing.service.CampaignApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.BaseUrl;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Mohsen on 29/06/16.
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    public CampaignApi provideCampaignApiService(Retrofit retrofit) {
        return retrofit.create(CampaignApi.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(BaseUrl baseUrl, Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .build();
    }


}
