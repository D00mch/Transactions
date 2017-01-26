package com.liverm0r.transactions.ui.common.error;


import android.content.Context;

import io.reactivex.functions.*;

import io.reactivex.Observable;

public interface IErrorHandler {

    void errorHappened(Throwable exception);

    Observable<Consumer<Context>> doOnError();

    Observable<String> showOnError();
}
