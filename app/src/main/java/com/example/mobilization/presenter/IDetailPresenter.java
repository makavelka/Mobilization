package com.example.mobilization.presenter;

import com.example.mobilization.model.data.Artist;

public interface IDetailPresenter {
    Artist getDataFromIntent();
    void showData(Artist artist);
}
