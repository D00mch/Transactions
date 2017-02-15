package com.liverm0r.transactions.data.api;


import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitUtils {

    private RetrofitUtils() {
    }

    @NonNull
    public static <S> S createService(@NonNull String url, Class<S> sClass) {
        return basicBuilder(url).build().create(sClass);
    }

    @NonNull
    protected static Retrofit.Builder basicBuilder(@NonNull String url) {
        return new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }
}