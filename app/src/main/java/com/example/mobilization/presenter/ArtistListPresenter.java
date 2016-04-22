package com.example.mobilization.presenter;


import android.os.Bundle;

import com.example.mobilization.di.App;
import com.example.mobilization.model.Model;
import com.example.mobilization.model.data.Artist;
import com.example.mobilization.view.IMainView;
import com.example.mobilization.view.IView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class ArtistListPresenter extends BasePresenter implements IArtistListPresenter {

    private static final String BUNDLE_ARTIST_KEY = "BUNDLE_ARTIST_KEY";

    @Inject
    Model model;

    private IMainView view;
    private Subscription subscription = Subscriptions.empty();
    private List<Artist> artistList;
    public ArtistListPresenter() {
        App.getComponent().inject(this);
    }

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
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Artist> data) {
                        artistList = data;
                        if (data != null && !data.isEmpty()) {
                            view.showList(data);
                        } else {
                            view.showEmptyList();
                        }
                    }
                });
    }

    private boolean isListEmpty() {
        return artistList == null || artistList.isEmpty();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, IView view) {
        App.getComponent().inject(this);
        this.view = (IMainView) view;
        if (savedInstanceState != null) {
            artistList = (List<Artist>) savedInstanceState.getSerializable(BUNDLE_ARTIST_KEY);
            if (!isListEmpty()) {
                ((IMainView) view).showList(artistList);
            }
        }
    }

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
