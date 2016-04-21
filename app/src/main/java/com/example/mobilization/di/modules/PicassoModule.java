package com.example.mobilization.di.modules;

import com.example.mobilization.model.api.PicassoService;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PicassoModule {
    @Provides
    @Singleton
    Picasso providePicasso() {
        return new PicassoService().getPicasso();
    }
}
