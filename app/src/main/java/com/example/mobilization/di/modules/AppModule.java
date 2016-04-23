package com.example.mobilization.di.modules;

import android.content.Context;

import com.example.mobilization.di.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль, предоставляющий контекст приложения
 */

@Module
public class AppModule {

    private App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApp;
    }

}
