package com.example.mobilization.di.modules;

import com.example.mobilization.Const;
import com.example.mobilization.model.api.ApiInterface;
import com.example.mobilization.model.api.ApiService;
import com.example.mobilization.model.api.PicassoService;
import com.squareup.picasso.Picasso;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ModelModule {

    @Provides
    @Singleton
    @Named(Const.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(Const.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    ApiInterface provideArtistService() {
        return new ApiService().getApiService();
    }

    @Provides
    @Singleton
    Picasso providePicasso() {
        return new PicassoService().getPicasso();
    }
}
