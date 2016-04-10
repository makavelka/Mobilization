package com.example.mobilization.presenter;

import android.graphics.Bitmap;
import android.os.Bundle;

public interface IMainPresenter {
    void getData();
    void onItemClick(String position, Bitmap image);
    void onCreate(Bundle savedInstanceState);
    void onSaveInstanceState(Bundle outState);
    void onStop();
}
