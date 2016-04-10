package com.example.mobilization.model;

import com.example.mobilization.model.api.ApiInterface;
import com.example.mobilization.model.api.RetrofitModule;
import com.example.mobilization.model.data.Artist;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ModelImpl implements Model {
    ApiInterface apiInterface = RetrofitModule.getApiInterface();

    @Override
    public Observable<List<Artist>> getArtistList() {
        return apiInterface.getArtists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
