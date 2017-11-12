package com.umeshcydv.redmartdemoapp.presenter;

import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.SingleEmitter;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.internal.operators.SingleObserveOn;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by umeshcydv on 11/11/17.
 */

public class BasePresenter implements Presenter {

    private CompositeSubscription mCompositeSubscription;

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        unSubscribeAll();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
        configureSubscription();
    }

    protected void unSubscribeAll() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription.clear();
            mCompositeSubscription = null;
        }
    }

    private CompositeSubscription configureSubscription() {
        if (mCompositeSubscription == null || mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription = new CompositeSubscription();
        }
        return mCompositeSubscription;
    }

    protected <T> void subscribe(Single<T> observable, SingleSubscriber<T> observer) {
        Subscription subscription = observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(observer);
        configureSubscription().add(subscription);
    }
}
