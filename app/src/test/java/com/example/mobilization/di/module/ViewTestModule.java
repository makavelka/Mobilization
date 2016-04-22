package com.example.mobilization.di.module;

import com.example.mobilization.presenter.ArtistListPresenter;
import com.example.mobilization.presenter.DetailPresenter;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class ViewTestModule  {

    @Provides
    ArtistListPresenter provideArtistListPresenter() {
        return mock(ArtistListPresenter.class);
    }

    @Provides
    DetailPresenter provideDetailPresenter() {
        return mock(DetailPresenter.class);
    }
}
