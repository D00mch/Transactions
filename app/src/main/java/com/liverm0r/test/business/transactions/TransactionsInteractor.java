package com.liverm0r.test.business.transactions;


import android.support.annotation.NonNull;

import com.liverm0r.test.business.transactions.products_fabric.IProductsFabric;
import com.liverm0r.test.data.model.Product;
import com.liverm0r.test.data.repositories.currency.ICurrencyQueryRepo;
import com.liverm0r.test.data.repositories.detail_transactions.IProductHolderRepo;
import com.liverm0r.test.ui.transactions.TransactionsModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

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
