package com.example.mobilization.presenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements Presenter {

    @Inject
    protected CompositeSubscription compositeSubscription;

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
