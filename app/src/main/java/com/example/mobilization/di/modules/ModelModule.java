package com.example.mobilization.di.modules;

import com.example.mobilization.Const;
import com.example.mobilization.model.api.ApiInterface;
import com.example.mobilization.model.api.ApiService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Модуль, предоставляющий вспомогательные данные для работы слоя Model
 */

@Module
public class ModelModule {

    /**
     * Предоставляет шедулер для Rx, который будет работать в главном потоке.
     * Используется для вывода полученных данных на экран.
     *
     * @return шедулер главного потока.
     */
    @Provides
    @Singleton
    @Named(Const.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return AndroidSchedulers.mainThread();
    }

    /**
     * Предоставляет шедулер для Rx, который будет работать с данными в фоне.
     * Используется для получения данных.
     *
     * @return шедулер для фоновой работы.
     */
    @Provides
    @Singleton
    @Named(Const.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }


    /**
     * Предоставляет интерфейс, который запрашивает данные по REST.
     *
     * @return интерфейс для получения данных.
     */
    @Provides
    @Singleton
    ApiInterface provideArtistService() {
        return new ApiService().getApiService();
    }

}
