package com.example.mobilization.di.module;

import com.example.mobilization.di.modules.PresenterModule;
import com.example.mobilization.model.Model;
import com.example.mobilization.view.IDetailView;
import com.example.mobilization.view.IMainView;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

import static org.mockito.Mockito.mock;

/**
 * Модуль, предоставляющий данные, для тестирования слоя Presenter
 */
@Module
public class PresenterTestModule extends PresenterModule {

    /**
     * Предоставляет заглушку слоя Model
     *
     * @return - Model
     */
    @Provides
    @Singleton
    Model provideModel() {
        return mock(Model.class);
    }

    /**
     * Предоставляет заглушку слоя View для первого экрана
     *
     * @return - View
     */
    @Provides
    @Singleton
    IMainView provideArtistListView() {
        return Mockito.mock(IMainView.class);
    }

    /**
     * Предоставляет заглушку слоя View для второго экрана
     *
     * @return - View
     */
    @Provides
    @Singleton
    IDetailView provideArtistDetailsView() {
        return Mockito.mock(IDetailView.class);
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}
