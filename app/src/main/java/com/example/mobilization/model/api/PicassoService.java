package com.example.mobilization.model.api;

import android.content.Context;

import com.example.mobilization.di.App;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.OkHttpClient;


public class PicassoService {

    @Inject
    Context mContext;

    public PicassoService() {
        App.getComponent().inject(this);
    }

    public Picasso getPicasso() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.cache(new Cache(mContext.getFilesDir(), Integer.MAX_VALUE));
        okHttpClient.addInterceptor(new ResponseCacheInterceptor());
        OkHttp3Downloader okHttpDownloader = new OkHttp3Downloader(okHttpClient.build());
        return new Picasso.Builder(mContext).downloader(okHttpDownloader).build();
    }
}
