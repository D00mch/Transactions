package com.liverm0r.transactions.ui.common.error;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.liverm0r.transactions.R;
import com.liverm0r.transactions.business.detail_transactions.EmptyCacheIOException;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class ErrorHandler implements IErrorHandler {

    private Context mContext; // app context to get String resourses

    private Subject<String> mStringSubject;
    private Subject<Consumer<Context>> mErrorActionSubject;

    public ErrorHandler(@NonNull Context context) {
        mContext = context;
        mStringSubject = PublishSubject.create();
        mErrorActionSubject = PublishSubject.create();

    }

    @Override public void errorHappened(Throwable exception) {
        if (exception instanceof EmptyCacheIOException) {
            mErrorActionSubject.onNext(context -> {
                if (context instanceof Activity) ((Activity) context).onBackPressed();
            });
        } else if (exception instanceof IOException) {
            mStringSubject.onNext(mContext.getString(R.string.exc_base_io));
        }
    }

    @Override public Observable<Consumer<Context>> doOnError() {
        return mErrorActionSubject;
    }

    @Override public Observable<String> showOnError() {
        return mStringSubject;
    }
}
