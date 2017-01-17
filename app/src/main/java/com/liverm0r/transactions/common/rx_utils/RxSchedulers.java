package com.liverm0r.transactions.common.rx_utils;


import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulers extends RxSchedulersAbs {

    @Override protected Scheduler getMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Override protected Scheduler getComputationScheduler() {
        return Schedulers.computation();
    }

    @Override protected Scheduler getIOScheduler() {
        return Schedulers.io();
    }
}
