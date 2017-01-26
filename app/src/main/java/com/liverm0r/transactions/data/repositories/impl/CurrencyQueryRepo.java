package com.liverm0r.transactions.data.repositories.impl;


import android.support.annotation.NonNull;

import com.liverm0r.transactions.data.api.ICurrencyApi;
import com.liverm0r.transactions.data.model.Rate;
import com.liverm0r.transactions.data.model.Transaction;
import com.liverm0r.transactions.data.repositories.BaseQueryRepo;
import com.liverm0r.transactions.data.repositories.ICurrencyQueryRepo;

import java.util.List;

import io.reactivex.Single;

public class CurrencyQueryRepo extends BaseQueryRepo implements ICurrencyQueryRepo {

    private ICurrencyApi mCurrencyApi;

    public CurrencyQueryRepo(ICurrencyApi currencyApi) {
        mCurrencyApi = currencyApi;
    }

    @Override public @NonNull Single<List<Rate>> getRates() {
        return convertResponse(mCurrencyApi.getRates());
    }

    @Override public @NonNull Single<List<Transaction>> getTransactions() {
        return convertResponse(mCurrencyApi.getTransactions());
    }
}
