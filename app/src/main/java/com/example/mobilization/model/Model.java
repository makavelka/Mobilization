package com.example.mobilization.model;

import com.example.mobilization.model.data.Artist;

import java.util.List;

import rx.Observable;

/**
 * Интерфейс, используемый для получения списка исполнителей
 */
public interface Model {
    Observable<List<Artist>> getArtistList();
}
