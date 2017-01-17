package com.liverm0r.transactions.data.api;


import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tientun.mockresponse.FakeInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtilsDebug {

    private RetrofitUtilsDebug() {
    }


    /**
     * @param url example: "http://mock.api"
     */
    public static <S> S createService(String url, Class<S> sClass, Context context) {
        return basicBuilder(url, context).build().create(sClass);
    }

    protected static Retrofit.Builder basicBuilder(String url, Context context) {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new FakeInterceptor(context))
                .build();

        return new Retrofit.Builder()
                //.baseUrl("http://mock.api") //"http://mock.api1"
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }
}
