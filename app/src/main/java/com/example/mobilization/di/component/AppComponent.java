package com.example.mobilization.di.component;

import com.example.mobilization.di.modules.ApiModule;
import com.example.mobilization.di.modules.ModelModule;
import com.example.mobilization.di.modules.PresenterModule;
import com.example.mobilization.di.modules.ViewModule;
import com.example.mobilization.model.ModelImpl;
import com.example.mobilization.presenter.ArtistListPresenter;
import com.example.mobilization.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, PresenterModule.class, ViewModule.class, ModelModule.class})
public interface AppComponent {
    void inject(ModelImpl model);
    void inject(ArtistListPresenter presenter);
    void inject(MainActivity activity);
}
