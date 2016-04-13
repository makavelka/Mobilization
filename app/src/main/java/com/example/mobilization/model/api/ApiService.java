package com.example.mobilization.model.api;

import android.content.Context;

import com.example.mobilization.Const;
import com.example.mobilization.di.App;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    @Inject
    Context mContext;

    public ApiService() {
        App.getComponent().inject(this);
    }

    public ApiInterface getApiService() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(new Cache(mContext.getFilesDir(), 5 * 1024 * 1024));   // 5 Мб
        client.addInterceptor(new ResponseCacheInterceptor());
        client.addNetworkInterceptor(new ResponseCacheInterceptor());
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }
}
