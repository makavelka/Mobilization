package com.example.mobilization.di.modules;

import com.example.mobilization.presenter.ArtistListPresenter;
import com.example.mobilization.presenter.DetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    ArtistListPresenter provideArtistListPresenter() {
        return new ArtistListPresenter();
    }

    @Provides
    DetailPresenter provideDetailPresenter() {
        return new DetailPresenter();
    }
}
