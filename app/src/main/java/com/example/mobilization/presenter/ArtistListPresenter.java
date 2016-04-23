package com.example.mobilization.presenter;


import android.content.Context;
import android.os.Bundle;

import com.example.mobilization.R;
import com.example.mobilization.di.App;
import com.example.mobilization.model.Model;
import com.example.mobilization.model.data.Artist;
import com.example.mobilization.view.IMainView;
import com.example.mobilization.view.IView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Реализация презентера основного экрана
 */
public class ArtistListPresenter extends BasePresenter implements IArtistListPresenter {

    //Ключ, по которому будет сохраняться и восстанавливаться состояние экрана
    private static final String BUNDLE_ARTIST_KEY = "BUNDLE_ARTIST_KEY";

    @Inject
    Model model;

    @Inject
    Context mContext;

    private IMainView view;
    private Subscription subscription = Subscriptions.empty();
    private List<Artist> artistList;

    /**
     * Конструктор для DI
     */
    @Inject
    public ArtistListPresenter() {
    }

    /**
     * Конструктор для тестов
     *
     * @param view - интерфейс экрана для мокирования
     */
    public ArtistListPresenter(IMainView view) {
        App.getComponent().inject(this);
        this.view = view;
    }

    //Получение данных из слоя Model
    @Override
    public void getData() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        subscription = model.getArtistList()
                .subscribe(new Observer<List<Artist>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showEmptyList();
                        if (e instanceof HttpException)
                            view.showError(mContext.getString(R.string.no_internet));
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Artist> data) {
                        artistList = data;
                        if (!isListEmpty()) {
                            //Вывод данных на экран
                            view.showList(artistList);
                        } else {
                            //Показываем пустой список
                            view.showEmptyList();
                        }
                    }
                });
    }

    /**
     * Проверка массива на пустоту
     *
     * @return true - если он не инициализирован или пустой
     */
    private boolean isListEmpty() {
        return artistList == null || artistList.isEmpty();
    }

    /**
     * Инициализация экрана и инъекция данных.
     *
     * @param savedInstanceState - bundle с данными для восстановления
     * @param view               - экран, с которым презентер будет взаимодействовать
     */
    @Override
    public void onCreate(Bundle savedInstanceState, IView view) {
        App.getComponent().inject(this);
        this.view = (IMainView) view;
        //Если есть сохраненное состояние экрана, то восстанавливаем его
        if (savedInstanceState != null) {
            artistList = (List<Artist>) savedInstanceState.getSerializable(BUNDLE_ARTIST_KEY);
            if (!isListEmpty()) {
                ((IMainView) view).showList(artistList);
            }
        }
    }

    /**
     * Сохранение состояния View и данных на экране, для их последующего восстановления, при
     * разрушении View.
     *
     * @param outState - bundle с данными для восстановления
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!isListEmpty()) {
            outState.putSerializable(BUNDLE_ARTIST_KEY, new ArrayList<>(artistList));
        }
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
