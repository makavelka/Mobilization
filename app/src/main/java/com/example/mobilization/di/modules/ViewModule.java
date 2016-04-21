package com.example.mobilization.di.modules;

import com.example.mobilization.presenter.ArtistListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    ArtistListPresenter provideArtistListPresenter() {
        return new ArtistListPresenter();
    }
}
