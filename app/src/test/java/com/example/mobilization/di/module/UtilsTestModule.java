package com.example.mobilization.di.module;

import com.example.mobilization.Utils;
import com.example.mobilization.di.modules.UtilsModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Модуль, предоставляющий заглушку вспомогательного класса
 */
@Module
public class UtilsTestModule extends UtilsModule {

    @Provides
    @Singleton
    Utils provideUtils() {
        return mock(Utils.class);
    }
}
