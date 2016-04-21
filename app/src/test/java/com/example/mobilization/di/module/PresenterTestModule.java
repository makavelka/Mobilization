package com.example.mobilization.di.module;

import com.example.mobilization.di.modules.PresenterModule;
import com.example.mobilization.model.Model;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

import static org.mockito.Mockito.mock;

@Module
public class PresenterTestModule extends PresenterModule{

    @Provides
    @Singleton
    Model provideModel() {
        return mock(Model.class);
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}
