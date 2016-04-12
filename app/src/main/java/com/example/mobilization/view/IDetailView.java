package com.example.mobilization.view;

import android.graphics.Bitmap;

import com.example.mobilization.model.data.Artist;

public interface IDetailView extends IView{
    Artist loadDataFromIntent();
    void showData(Artist artist);
    Bitmap loadSmallImage();
}
