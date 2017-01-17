package com.liverm0r.test.dagger.currency.detail_transactions;


import com.liverm0r.test.business.detail_transactions.DetailTransInteractor;
import com.liverm0r.test.business.detail_transactions.IDetailTransInteractor;
import com.liverm0r.test.data.repositories.detail_transactions.IProductHolderRepo;

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

