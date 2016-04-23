package com.example.mobilization.model.api;

import android.content.Context;

import com.example.mobilization.di.App;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Класс, используемый для настройки Picasso - библиотеки, работающей с загрузкой изображений
 */
public class PicassoService {

    //Инъекция контекста, необходим для инициализации кэша
    @Inject
    Context mContext;
    //Вызов метода inject в конструкторе класса, чтобы инъектируемые данные были доступны сразу
    public PicassoService() {
        App.getComponent().inject(this);
    }

    /**
     * Настройка Picasso
     * @return экземпляр Picasso
     */
    public Picasso getPicasso() {
        //Создание OkHttpClient, который будет использоваться как загрузчик изображений с интернета
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        //Установка кэша для OkHttpClient в размере 2 гб
        okHttpClient.cache(new Cache(mContext.getFilesDir(), Integer.MAX_VALUE));
        //Добавление интерсептора, кэширующего получаемые данные
        okHttpClient.addInterceptor(new ResponseCacheInterceptor());
        //Установка OkHttpClient стандартным загрузчиком изображений из интернета
        OkHttp3Downloader okHttpDownloader = new OkHttp3Downloader(okHttpClient.build());
        return new Picasso.Builder(mContext).downloader(okHttpDownloader).build();
    }
}
