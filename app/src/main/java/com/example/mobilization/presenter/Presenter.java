package com.example.mobilization.presenter;

import android.os.Bundle;

public interface Presenter {
    void getData();
    void onItemClick();
    void onCreate(Bundle savedInstanceState);
    void onSaveInstanceState(Bundle outState);
}
