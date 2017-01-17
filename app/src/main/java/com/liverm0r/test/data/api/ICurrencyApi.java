package com.liverm0r.test.data.api;


import com.liverm0r.test.data.model.Rate;
import com.liverm0r.test.data.model.Transaction;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ICurrencyApi {

    static String API_VER = "api1";

    @GET("/" + API_VER + "/currency/rates")
    Observable<Response<List<Rate>>> getRates();

    @GET("/" + API_VER + "/currency/transactions")
    Observable<Response<List<Transaction>>> getTransactions();
}
