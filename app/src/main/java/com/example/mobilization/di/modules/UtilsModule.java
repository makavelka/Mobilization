package com.example.mobilization.di.modules;

import com.example.mobilization.Utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Модуль, предоставляющий вспомогательные классы
 */
@Module
public class UtilsModule {

    @Provides
    @Singleton
    Utils provideUtils() {
        return new Utils();
    }
}
