package com.example.mobilization.di.module;

import com.example.mobilization.presenter.ArtistListPresenter;
import com.example.mobilization.presenter.DetailPresenter;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Модуль, предоставляющий презентеры для тестирования слоя View
 */
@Module
public class ViewTestModule  {

    /**
     * Возвращает заглушку презентера для первого экрана
     * @return - презентер
     */
    @Provides
    ArtistListPresenter provideArtistListPresenter() {
        return mock(ArtistListPresenter.class);
    }

    /**
     * Возвращает заглушку презентера для второго экрана
     * @return - презентер
     */
    @Provides
    DetailPresenter provideDetailPresenter() {
        return mock(DetailPresenter.class);
    }
}
