package com.liverm0r.test.dagger.application;


import com.liverm0r.test.common.rx_utils.RxSchedulers;
import com.liverm0r.test.common.rx_utils.RxSchedulersAbs;

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
