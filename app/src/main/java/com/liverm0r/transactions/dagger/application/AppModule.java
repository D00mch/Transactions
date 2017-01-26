package com.liverm0r.transactions.dagger.application;


import android.content.Context;
import android.support.annotation.NonNull;

import com.liverm0r.transactions.common.error.ErrorHandler;
import com.liverm0r.transactions.common.error.IErrorHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context mContext;

    public AppModule(@NonNull Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return mContext;
    }

    @Provides
    @Singleton
    IErrorHandler provideErrorHandler() {
        return new ErrorHandler(mContext);
    }
}
