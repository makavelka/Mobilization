package com.example.mobilization.model.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitModule {

    public static ApiInterface getApiInterface() {

        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl("https://cache-stav01.cdn.yandex.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }
}
