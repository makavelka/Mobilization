package com.example.mobilization.model.api;

import com.example.mobilization.model.data.Artist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

public interface ApiInterface {

    @GET("/download.cdn.yandex.net/mobilization-2016/artists.json")
    Observable<List<Artist>> getArtists();
}
