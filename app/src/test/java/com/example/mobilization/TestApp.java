package com.example.mobilization;

import com.example.mobilization.di.App;
import com.example.mobilization.di.module.AppTestModule;
import com.example.mobilization.di.DaggerTestComponent;
import com.example.mobilization.di.component.AppComponent;

public class TestApp extends App {

    /**
     * Метод собирающий граф зависимостей для DI.
     * Вызывается в классе Application для доступа по всему приложению.
     * @return граф зависимостей DI
     */
    @Override
    protected AppComponent buildComponent() {
        return DaggerTestComponent.builder()
                .appTestModule(new AppTestModule(this))
                .build();
    }
}