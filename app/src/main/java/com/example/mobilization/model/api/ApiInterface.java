package com.example.mobilization.model.api;

import com.example.mobilization.model.data.Artist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Интерфейс, используемый библиотекой Retrofit, для создания REST запросов на их основе
 */
public interface ApiInterface {

    /**
     * Получение тестовых данных, который используются для вывода на экран
     * @return Observable со списком исполнителей
     */
    @GET("/download.cdn.yandex.net/mobilization-2016/artists.json")
    Observable<List<Artist>> getArtists();
}
