package com.liverm0r.transactions.data.repositories;


import android.support.annotation.NonNull;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;

public class BaseQueryRepo {

    protected @NonNull <T> Single<T> converResponse(@NonNull Observable<Response<T>> observable) {
        return observable.firstOrError()
                .map(response -> {
                    if (response.isSuccessful()) return response.body();
                    else throw new IOException(response.message());
                });
    }
}
