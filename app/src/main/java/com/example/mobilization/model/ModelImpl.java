package com.example.mobilization.model;

import com.example.mobilization.Const;
import com.example.mobilization.di.App;
import com.example.mobilization.model.api.ApiInterface;
import com.example.mobilization.model.data.Artist;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;


/**
 * Реализация интерфейса {@link Model}
 */
public class ModelImpl implements Model {

    @Inject
    protected ApiInterface apiInterface;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public ModelImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public Observable<List<Artist>> getArtistList() {
        return apiInterface.getArtists()
                .subscribeOn(ioThread)
                .observeOn(uiThread);
    }
}
