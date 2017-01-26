package com.liverm0r.transactions.dagger.currency;


import android.content.Context;

import com.liverm0r.transactions.business.interactors.ITransactionsInteractor;
import com.liverm0r.transactions.business.interactors.impl.TransactionsInteractor;
import com.liverm0r.transactions.business.helpers.products_fabric.ProductsInPoundsFabric;
import com.liverm0r.transactions.data.api.ICurrencyApi;
import com.liverm0r.transactions.data.api.RetrofitUtilsDebug;
import com.liverm0r.transactions.data.repositories.impl.CurrencyQueryRepo;
import com.liverm0r.transactions.data.repositories.ICurrencyQueryRepo;
import com.liverm0r.transactions.data.repositories.IProductHolderRepo;
import com.liverm0r.transactions.data.repositories.impl.ProductHolderRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class CurrencyModule {

    @Provides
    @CurrencyScope
    ICurrencyApi provideCurrencyApi(Context context) {
        return RetrofitUtilsDebug.createService(
                "http://mock.api",
                ICurrencyApi.class,
                context);
    }

    @Provides
    @CurrencyScope
    ICurrencyQueryRepo provideCurrencyQueryRepo(ICurrencyApi api) {
        return new CurrencyQueryRepo(api);
    }

    @Provides
    @CurrencyScope
    IProductHolderRepo provideProductHolderRepo() {
        return new ProductHolderRepo();
    }

    @Provides
    @CurrencyScope
    ITransactionsInteractor provideTransactionsInteractor(ICurrencyQueryRepo currencyQueryRepo,
                                                          IProductHolderRepo productHolderRepo) {
        return new TransactionsInteractor(currencyQueryRepo,
                productHolderRepo,
                new ProductsInPoundsFabric());
    }
}
