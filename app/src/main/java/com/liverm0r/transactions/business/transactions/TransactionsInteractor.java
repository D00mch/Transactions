package com.liverm0r.transactions.business.transactions;


import android.support.annotation.NonNull;

import com.liverm0r.transactions.business.transactions.products_fabric.IProductsFabric;
import com.liverm0r.transactions.data.model.Product;
import com.liverm0r.transactions.data.repositories.currency.ICurrencyQueryRepo;
import com.liverm0r.transactions.data.repositories.detail_transactions.IProductHolderRepo;
import com.liverm0r.transactions.ui.transactions.TransactionsModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class TransactionsInteractor implements ITransactionsInteractor {

    private ICurrencyQueryRepo mCurrencyQueryRepo;
    private IProductHolderRepo mProductHolderRepo;
    private IProductsFabric mProductsInPoundsFabric;

    public TransactionsInteractor(@NonNull ICurrencyQueryRepo currencyQueryRepo,
                                  @NonNull IProductHolderRepo productHolderRepo,
                                  @NonNull IProductsFabric productsInPoundsFabric) {
        mCurrencyQueryRepo = currencyQueryRepo;
        mProductHolderRepo = productHolderRepo;
        mProductsInPoundsFabric = productsInPoundsFabric;
//        mExchanger = new ExchangeImp();
    }

    @Override @NonNull public Single<List<TransactionsModel>> getTransactions() {

        return mCurrencyQueryRepo.getRates()
                .observeOn(Schedulers.computation())
                .zipWith(mCurrencyQueryRepo.getTransactions(), (rates, transactions) ->
                        mProductsInPoundsFabric.get(transactions, rates))
                .map(products -> {
                    mProductHolderRepo.setProducts(products);
                    return products;
                })
                .map(this::buildTransactionModels);
    }

    @Override public void skuChosen(@NonNull String sku) {
        mProductHolderRepo.setCurrentSku(sku);
    }

    private List<TransactionsModel> buildTransactionModels(List<Product> products) {
        ArrayList<TransactionsModel> transactionsModels = new ArrayList<>(products.size());
        for (Product product : products) {
            transactionsModels.add(
                    new TransactionsModel(product.getSku(),
                            product.getTransactions().size(),
                            "transactions"));
        }
        return transactionsModels;
    }
}