package com.example.mobilization.di.modules;

import com.example.mobilization.presenter.ArtistListPresenter;
import com.example.mobilization.presenter.DetailPresenter;

import dagger.Module;
import dagger.Provides;


/**
 * Модуль, предоставляющий презентеры для слоя View
 */
@Module
public class ViewModule {

    /**
     * Предоставляет презентер для первого экрана
     *
     * @return презентер
     */
    @Provides
    ArtistListPresenter provideArtistListPresenter() {
        return new ArtistListPresenter();
    }


    /**
     * Предоставляет презентер для второго экрана
     *
     * @return презентер
     */
    @Provides
    DetailPresenter provideDetailPresenter() {
        return new DetailPresenter();
    }
}
