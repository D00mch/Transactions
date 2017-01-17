package com.liverm0r.transactions.ui.common.error;


import android.content.Context;

import io.reactivex.functions.*;

import io.reactivex.Observable;

public interface IErrorHandler {

    public void errorHappened(Throwable exception);

    public Observable<Consumer<Context>> doOnError();

    public Observable<String> showOnError();
}
