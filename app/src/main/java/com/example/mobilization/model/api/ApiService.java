package com.example.mobilization.model.api;

import android.content.Context;

import com.example.mobilization.Const;
import com.example.mobilization.di.App;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Класс, используемый для настройки Retrofit, и реализующий интерфейсы для получения данных
 */
public class ApiService {

    //Инъекция контекста, необходим для инициализации кэща
    @Inject
    Context mContext;

    //Вызов метода inject в конструкторе класса, чтобы инъектируемые данные были доступны сразу
    public ApiService() {
        App.getComponent().inject(this);
    }


    /**
     * Реализация интерфейся, получающего данные
     * @return Observable со списком исполнителей
     */
    public ApiInterface getApiService() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        //Установка кэша для OkHttpClient в размере 5 мб
        client.cache(new Cache(mContext.getFilesDir(), 5 * 1024 * 1024));
        //Добавление интерсептора, кэширующего получаемые данные
        client.addInterceptor(new ResponseCacheInterceptor());
        client.addNetworkInterceptor(new ResponseCacheInterceptor());
        //Создание экземпляра Retrofit
        Retrofit.Builder builder = new Retrofit.Builder()
                //Установка базового url
                .baseUrl(Const.BASE_URL)
                //Установка стандартного клиента
                .client(client.build())
                //Установка фабрики, которая будет конвертировать полученные данные в POJO
                .addConverterFactory(GsonConverterFactory.create())
                //Установка фабрики, для работы с RxJava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        //Реализация интерфейса, получающего тестовые данные
        ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }
}
