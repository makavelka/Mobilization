package com.example.mobilization.di.modules;

import com.example.mobilization.Utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    Utils provideUtils() {
        return new Utils();
    }
}
