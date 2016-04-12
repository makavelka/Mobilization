package com.example.mobilization.view;

import android.content.Context;

public interface IView {
    Context getContext();
    void showToast(String message);
}
