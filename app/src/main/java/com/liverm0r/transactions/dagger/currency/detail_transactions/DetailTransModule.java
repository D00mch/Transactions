package com.liverm0r.transactions.dagger.currency.detail_transactions;


import com.liverm0r.transactions.business.detail_transactions.DetailTransInteractor;
import com.liverm0r.transactions.business.detail_transactions.IDetailTransInteractor;
import com.liverm0r.transactions.data.repositories.detail_transactions.IProductHolderRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailTransModule {

    @Provides
    @DetailTransScope
    IDetailTransInteractor provideDetailTransInteractor(IProductHolderRepo repo) {
        return new DetailTransInteractor(repo);
    }
}

