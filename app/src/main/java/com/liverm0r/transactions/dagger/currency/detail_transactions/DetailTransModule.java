package com.liverm0r.transactions.dagger.currency.detail_transactions;


import com.liverm0r.transactions.business.interactors.impl.DetailTransInteractor;
import com.liverm0r.transactions.business.interactors.IDetailTransInteractor;
import com.liverm0r.transactions.data.repositories.IProductHolderRepo;

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

