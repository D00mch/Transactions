package com.liverm0r.test.common.rx_utils;


import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class RxTestSchedulers extends RxSchedulersAbs {

    @Override protected Scheduler getMainThreadScheduler() {
        return Schedulers.trampoline();
    }

    @Override protected Scheduler getIOScheduler() {
        return Schedulers.trampoline();
    }

    @Override protected Scheduler getComputationScheduler() {
        return Schedulers.trampoline();
    }
}
