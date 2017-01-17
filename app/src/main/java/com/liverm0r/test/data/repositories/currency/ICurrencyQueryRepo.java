package com.liverm0r.test.data.repositories.currency;


import com.liverm0r.test.data.model.Rate;
import com.liverm0r.test.data.model.Transaction;

import java.util.List;

import io.reactivex.Single;

public interface ICurrencyQueryRepo {

    Single<List<Rate>> getRates();

    Single<List<Transaction>> getTransactions();
}
