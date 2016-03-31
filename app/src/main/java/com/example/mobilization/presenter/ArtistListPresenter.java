package com.example.mobilization.presenter;


import android.os.Bundle;

import com.example.mobilization.ModelImpl;
import com.example.mobilization.model.Model;
import com.example.mobilization.model.data.Artist;
import com.example.mobilization.view.IMainView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class ArtistListPresenter implements Presenter {

    private static final String BUNDLE_ARTIST_KEY = "BUNDLE_ARTIST_KEY";

    private Model model = new ModelImpl();

    private IMainView view;
    private Subscription subscription = Subscriptions.empty();

    private List<Artist> artistList;

    public ArtistListPresenter(IMainView view) {
        this.view = view;
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
                        view.showError(e.getMessage());
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

    @Override
    public void onItemClick() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            artistList = (List<Artist>) savedInstanceState.getSerializable(BUNDLE_ARTIST_KEY);
            if (!isListEmpty()) {
                view.showList(artistList);
            }
        }

    }

    private boolean isListEmpty() {
        return artistList == null || artistList.isEmpty();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (!isListEmpty()) {
            outState.putSerializable(BUNDLE_ARTIST_KEY, new ArrayList<>(artistList));
        }
    }
}
