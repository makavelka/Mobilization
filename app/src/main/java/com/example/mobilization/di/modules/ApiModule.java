package com.example.mobilization.di.modules;

import com.example.mobilization.Const;
import com.example.mobilization.model.api.ApiInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    ApiInterface provideApiInterface() {
        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }

}
