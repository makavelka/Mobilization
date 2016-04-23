package com.example.mobilization.di.modules;

import com.example.mobilization.model.Model;
import com.example.mobilization.model.ModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * Модуль, предоставляющий данные, для работы слоя Presenter
 */
@Module
public class PresenterModule {


    /**
     * Предоставляет экземпляр класса, использущегося для получения данных со слоя Model
     * @return
     */
    @Provides
    @Singleton
    Model provideDataRepository() {
        return new ModelImpl();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}
