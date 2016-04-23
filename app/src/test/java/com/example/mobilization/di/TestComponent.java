package com.example.mobilization.di;

import com.example.mobilization.di.component.AppComponent;
import com.example.mobilization.di.module.AppTestModule;
import com.example.mobilization.di.module.ModelTestModule;
import com.example.mobilization.di.module.PicassoTestModule;
import com.example.mobilization.di.module.PresenterTestModule;
import com.example.mobilization.di.module.UtilsTestModule;
import com.example.mobilization.di.module.ViewTestModule;
import com.example.mobilization.model.ApiInterfaceTest;
import com.example.mobilization.model.ModelImplTest;
import com.example.mobilization.presenter.ArtistListPresenterTest;
import com.example.mobilization.presenter.DetailPresenterTest;
import com.example.mobilization.view.AdapterTest;
import com.example.mobilization.view.MainActivityTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelTestModule.class, PresenterTestModule.class, ViewTestModule.class, AppTestModule.class, UtilsTestModule.class, PicassoTestModule.class})
public interface TestComponent extends AppComponent {
    void inject(ArtistListPresenterTest presenterTest);
    void inject(ModelImplTest modelImplTest);
    void inject(ApiInterfaceTest apiInterfaceTest);
    void inject(MainActivityTest mainActivityTest);
    void inject(DetailPresenterTest detailPresenterTest);
    void inject(AdapterTest adapterTest);
}
