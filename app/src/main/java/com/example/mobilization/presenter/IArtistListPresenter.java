package com.example.mobilization.presenter;

import android.graphics.Bitmap;

public interface IArtistListPresenter extends Presenter{
    void getData();
    void onItemClick(String position, Bitmap image);
    void onStop();
}
