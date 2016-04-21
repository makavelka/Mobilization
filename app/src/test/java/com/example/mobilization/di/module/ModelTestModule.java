package com.example.mobilization.di.module;

import com.example.mobilization.Const;
import com.example.mobilization.TestUtils;
import com.example.mobilization.di.modules.ModelModule;
import com.example.mobilization.model.api.ApiInterface;
import com.example.mobilization.model.data.Artist;

import java.util.Arrays;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;

@Module
public class ModelTestModule extends ModelModule {

    private TestUtils testUtils;

    public ModelTestModule() {
        testUtils = new TestUtils();
    }

    @Provides
    @Singleton
    ApiInterface provideApiInterface() {
        return mock(ApiInterface.class);
    }

    @Provides
    @Singleton
    @Named(Const.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return Schedulers.immediate();
    }

    @Provides
    @Singleton
    @Named(Const.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.immediate();
    }

    @Provides
    @Singleton
    List<Artist> provideArtistList() {
        Artist[] artists = testUtils.getGson().fromJson(testUtils.readString("json/test.json"), Artist[].class);
        return Arrays.asList(artists);
    }
}