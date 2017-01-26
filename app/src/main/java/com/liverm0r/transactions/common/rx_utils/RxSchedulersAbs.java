package com.liverm0r.transactions.common.rx_utils;


import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.SingleTransformer;

public abstract class RxSchedulersAbs {

    abstract protected Scheduler getMainThreadScheduler();

    abstract protected Scheduler getIOScheduler();

    abstract protected Scheduler getComputationScheduler();

    private ObservableTransformer<Object, Object> mIOToMainTransformer;
    private ObservableTransformer<Object, Object> mComputationToMainTransformer;

    {
        mIOToMainTransformer = build(getIOScheduler());
        mComputationToMainTransformer = build(getComputationScheduler());
    }

    //—————————————————————————————————————————————————————————————————————— API

    private <T> ObservableTransformer<T, T> build(Scheduler subscribeScheduler) {
        return upstream -> upstream
                .subscribeOn(subscribeScheduler)
                .observeOn(getMainThreadScheduler());
    }

    private <T> SingleTransformer<T, T> g() {
        return upstream -> upstream;
    }

    public <T> ObservableTransformer<T, T> getIOToMainTransformer() {
        //noinspection unchecked
        return (ObservableTransformer<T, T>) mIOToMainTransformer;
    }

    public <T> ObservableTransformer<T, T> getComputationToMainTransformer() {
        //noinspection unchecked
        return (ObservableTransformer<T, T>) mIOToMainTransformer;
    }
}
