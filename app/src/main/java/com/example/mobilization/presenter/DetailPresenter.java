package com.example.mobilization.presenter;

import android.os.Bundle;

import com.example.mobilization.model.data.Artist;
import com.example.mobilization.view.IDetailView;
import com.example.mobilization.view.IView;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Реализация презентера для второго экрана
 */
public class DetailPresenter implements IDetailPresenter {

    private static final String BUNDLE_DETAIL_KEY = "BUNDLE_DETAIL_KEY";
    private Subscription subscription = Subscriptions.empty();
    private IDetailView view;
    private Artist artist;

    /**
     * Конструктор для DI
     */
    @Inject
    public DetailPresenter() {
    }

    /**
     * Конструктор для тестов
     * @param view - интерфейс экрана для мокирования
     * @param artist
     */
    public DetailPresenter(IDetailView view, Artist artist) {
        this.view = view;
        this.artist = artist;
    }

    /**
     * Метод для вывода данных на экран
     * @param artist - данные для вывода
     */
    @Override
    public void showData(Artist artist) {
        if (artist != null)
            view.showData(artist);
        else showError();
    }

    @Override
    public void showError() {
        view.showError();
    }

    /**
     * Инициализация экрана и инъекция данных.
     * @param savedInstanceState - bundle с данными для восстановления
     * @param view - экран, с которым презентер будет взаимодействовать
     */
    @Override
    public void onCreate(Bundle savedInstanceState, IView view) {
        this.view = (IDetailView) view;
        if (savedInstanceState != null) {
            artist = (Artist) savedInstanceState.getSerializable(BUNDLE_DETAIL_KEY);
            if (artist != null) {
                ((IDetailView) view).showData(artist);
            }
        }
    }

    /**
     * Сохранение состояния View и данных на экране, для их последующего восстановления, при
     * разрушении View.
     * @param outState - bundle с данными для восстановления
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (artist != null) {
            outState.putParcelable(BUNDLE_DETAIL_KEY, artist);
        }
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
