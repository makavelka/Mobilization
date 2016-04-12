package com.example.mobilization.presenter;

import android.content.Context;

import com.example.mobilization.model.data.Artist;

public interface IDetailPresenter extends Presenter {
    Artist getDataFromIntent(Context context);
    void showData(Artist artist);
}
