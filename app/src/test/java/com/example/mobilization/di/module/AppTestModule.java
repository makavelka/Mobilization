package com.example.mobilization.di.module;

import android.content.Context;

import com.example.mobilization.TestApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль, предоставляющий контекст приложения
 */

@Module
public class AppTestModule {
    private TestApp mApp;

    public AppTestModule(TestApp app) {
        mApp = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApp;
    }
}
