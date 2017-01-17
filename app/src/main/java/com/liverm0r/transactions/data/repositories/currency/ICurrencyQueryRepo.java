package com.liverm0r.transactions.data.repositories.currency;


import com.liverm0r.transactions.data.model.Rate;
import com.liverm0r.transactions.data.model.Transaction;

import java.util.List;

import io.reactivex.Single;

public interface ICurrencyQueryRepo {

    Single<List<Rate>> getRates();

    Single<List<Transaction>> getTransactions();
}
