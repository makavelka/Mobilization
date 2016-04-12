package com.example.mobilization.presenter;

import android.os.Bundle;

import com.example.mobilization.view.IView;

public interface Presenter {
    void onCreate(Bundle savedInstanceState, IView view);
    void onSaveInstanceState(Bundle outState);
    void onStop();
}
