package com.example.mobilization.presenter;

import android.os.Bundle;

import com.example.mobilization.model.data.Artist;
import com.example.mobilization.view.IDetailView;
import com.example.mobilization.view.IView;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class DetailPresenter implements IDetailPresenter {

    private static final String BUNDLE_DETAIL_KEY = "BUNDLE_DETAIL_KEY";
    private Subscription subscription = Subscriptions.empty();
    private IDetailView view;
    private Artist artist;

    @Override
    public void showData(Artist artist) {
        view.showData(artist);
    }

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
