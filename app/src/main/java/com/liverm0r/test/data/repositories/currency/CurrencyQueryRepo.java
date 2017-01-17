package com.liverm0r.test.data.repositories.currency;


import android.support.annotation.NonNull;

import com.liverm0r.test.data.api.ICurrencyApi;
import com.liverm0r.test.data.model.Rate;
import com.liverm0r.test.data.model.Transaction;
import com.liverm0r.test.data.repositories.BaseQueryRepo;

import java.util.List;

import io.reactivex.Single;

public class CurrencyQueryRepo extends BaseQueryRepo implements ICurrencyQueryRepo {

    private ICurrencyApi mCurrencyApi;

    public CurrencyQueryRepo(ICurrencyApi currencyApi) {
        mCurrencyApi = currencyApi;
    }

    @Override public @NonNull Single<List<Rate>> getRates() {
        return converResponse(mCurrencyApi.getRates());
    }

    @Override public @NonNull Single<List<Transaction>> getTransactions() {
        return converResponse(mCurrencyApi.getTransactions());
    }
}
