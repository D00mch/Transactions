package com.liverm0r.transactions.business.transactions;


import android.support.annotation.NonNull;

import com.liverm0r.transactions.ui.transactions.TransactionsModel;

import java.util.List;

import io.reactivex.Single;

public interface ITransactionsInteractor {

    @NonNull Single<List<TransactionsModel>> getTransactions();

    void skuChosen(@NonNull String sku);
}
