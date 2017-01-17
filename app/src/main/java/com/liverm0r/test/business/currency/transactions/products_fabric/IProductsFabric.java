package com.liverm0r.test.business.currency.transactions.products_fabric;


import android.support.annotation.NonNull;

import com.liverm0r.test.data.model.Product;
import com.liverm0r.test.data.model.Rate;
import com.liverm0r.test.data.model.Transaction;

import java.util.List;

public interface IProductsFabric {

    @NonNull List<Product> get(@NonNull List<Transaction> transactions,
                               @NonNull List<Rate> rates);
}
