package com.example.mobilization.di;

import android.app.Application;

import com.example.mobilization.di.component.AppComponent;
import com.example.mobilization.di.component.DaggerAppComponent;

public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .build();
    }
}
