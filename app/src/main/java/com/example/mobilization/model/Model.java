package com.example.mobilization.model;

import com.example.mobilization.model.data.Artist;

import java.util.List;

import rx.Observable;

public interface Model {
    Observable<List<Artist>> getArtistList();
}
