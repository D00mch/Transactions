package com.liverm0r.test.dagger.currency;


import android.content.Context;

import com.liverm0r.test.business.transactions.ITransactionsInteractor;
import com.liverm0r.test.business.transactions.TransactionsInteractor;
import com.liverm0r.test.business.transactions.products_fabric.ProductsInPoundsFabric;
import com.liverm0r.test.data.api.ICurrencyApi;
import com.liverm0r.test.data.api.RetrofitUtilsDebug;
import com.liverm0r.test.data.repositories.currency.CurrencyQueryRepo;
import com.liverm0r.test.data.repositories.currency.ICurrencyQueryRepo;
import com.liverm0r.test.data.repositories.detail_transactions.IProductHolderRepo;
import com.liverm0r.test.data.repositories.detail_transactions.ProductHolderRepo;

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
