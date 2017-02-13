package com.liverm0r.transactions.dagger.currency;


import com.liverm0r.transactions.dagger.currency.detail_transactions.DetailTransComponent;
import com.liverm0r.transactions.dagger.currency.transactions.TransactionsComponent;

import dagger.Subcomponent;

@Subcomponent(modules = {CurrencyModule.class})
@CurrencyScope
public interface CurrencyComponent {

    TransactionsComponent.Builder transactionComponentBuilder();
    DetailTransComponent.Builder detailTransComponentBuilder();

    @Subcomponent.Builder
    interface Builder {
        CurrencyComponent.Builder currencyModule(CurrencyModule module);
        CurrencyComponent build();
    }
}
