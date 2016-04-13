package com.example.mobilization.di.component;

import com.example.mobilization.Utils;
import com.example.mobilization.di.modules.AppModule;
import com.example.mobilization.di.modules.ModelModule;
import com.example.mobilization.di.modules.PresenterModule;
import com.example.mobilization.di.modules.UtilsModule;
import com.example.mobilization.di.modules.ViewModule;
import com.example.mobilization.model.ModelImpl;
import com.example.mobilization.model.api.ApiService;
import com.example.mobilization.model.api.ResponseCacheInterceptor;
import com.example.mobilization.presenter.ArtistListPresenter;
import com.example.mobilization.presenter.BasePresenter;
import com.example.mobilization.view.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PresenterModule.class, ViewModule.class, ModelModule.class, AppModule.class, UtilsModule.class})
public interface AppComponent {
    void inject(ModelImpl model);
    void inject(ArtistListPresenter presenter);
    void inject(MainActivity activity);
    void inject(BasePresenter presenter);
    void inject(Utils utils);
    void inject(ResponseCacheInterceptor interceptor);
    void inject(ApiService api);
}
