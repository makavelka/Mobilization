package com.example.mobilization.di;

import android.app.Application;

import com.example.mobilization.di.component.AppComponent;
import com.example.mobilization.di.component.DaggerAppComponent;
import com.example.mobilization.di.modules.AppModule;

public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    /**
     * Создание графа зависимостей DI при старте приложения
     */
    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    /**
     * Метод собирающий граф зависимостей для DI.
     * Вызывается в классе Application для доступа по всему приложению.
     * @return граф зависимостей DI
     */
    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(App.this))
                .build();
    }
}
