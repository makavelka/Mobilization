package com.example.mobilization.di.module;

import com.example.mobilization.presenter.ArtistListPresenter;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class ViewTestModule  {

    @Provides
    ArtistListPresenter provideArtistListPresenter() {
        return mock(ArtistListPresenter.class);
    }
}
