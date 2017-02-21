package com.liverm0r.transactions.presentation.ui_base;


import android.content.Context;
import android.support.annotation.NonNull;

import com.liverm0r.transactions.common.error.IErrorHandler;
import com.liverm0r.transactions.common.rx_utils.RxSchedulersAbs;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;

public abstract class BaseViewModelAbs {

    private IErrorHandler mErrorHandler;
    private BehaviorSubject<Boolean> mProgressSubj;
    private RxSchedulersAbs mRxSchedulers;

    private CompositeDisposable mDisposables;

    public BaseViewModelAbs(RxSchedulersAbs rxSchedulers, IErrorHandler errorHandler) {
        mRxSchedulers = rxSchedulers;
        mErrorHandler = errorHandler;
        mProgressSubj = BehaviorSubject.create();

        mDisposables = new CompositeDisposable();
    }

    void onViewDestroyed() {
        mDisposables.clear();
    }

    //—————————————————————————————————————————————————————————————————————— bindings

    protected <T> void bindAction(@NonNull Observable<T> observable, @NonNull Consumer<T> onNext) {
        Disposable subscribe = observable.compose(mRxSchedulers.getIOToMainTransformer())
                .subscribe(onNext, this::onError);
        mDisposables.add(subscribe);
    }

    //—————————————————————————————————————————————————————————————————————— progress

    protected boolean checkIfLoadingAndShowProgress() {
        if (mProgressSubj.getValue() != null && mProgressSubj.getValue()) return true;
        mProgressSubj.onNext(true);
        return false;
    }

    protected void showProgress(boolean show) {
        mProgressSubj.onNext(show);
    }

    public @NonNull Observable<Boolean> progress() {
        return mProgressSubj;
    }

    //—————————————————————————————————————————————————————————————————————— errors

    protected void onError(Throwable throwable) {
        mErrorHandler.errorHappened(throwable);
    }

    public @NonNull Observable<Consumer<Context>> errorAction() {
        return mErrorHandler.doOnError();
    }

    public @NonNull Observable<String> errorMessage() {
        return mErrorHandler.showOnError();
    }
}
