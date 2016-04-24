package com.example.mobilization.presenter;

import com.example.mobilization.model.data.Artist;

/**
 * Интерфейс презентера второго экрана
 */
public interface IDetailPresenter extends Presenter {
    void showData(Artist artist);
    void showError();
}
