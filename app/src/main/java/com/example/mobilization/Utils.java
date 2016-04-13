package com.example.mobilization;

import android.content.Context;
import android.net.ConnectivityManager;

import com.example.mobilization.di.App;

import javax.inject.Inject;

public class Utils {

    @Inject
    Context mContext;

    public Utils() {
        App.getComponent().inject(this);
    }

    /**
     * Проверить наличие подключения к интернету.
     */
    public boolean isInternetConnected() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
