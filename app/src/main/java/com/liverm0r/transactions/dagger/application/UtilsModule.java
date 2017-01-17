package com.liverm0r.transactions.dagger.application;


import com.liverm0r.transactions.common.rx_utils.RxSchedulers;
import com.liverm0r.transactions.common.rx_utils.RxSchedulersAbs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Singleton
    @Provides
    RxSchedulersAbs provideRxSchedulers() {
        return new RxSchedulers();
    }
}
