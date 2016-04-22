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

@Module
public class PresenterTestModule extends PresenterModule{

    @Provides
    @Singleton
    Model provideModel() {
        return mock(Model.class);
    }

    @Provides
    @Singleton
    IMainView provideArtistListView() {
        return Mockito.mock(IMainView.class);
    }

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
