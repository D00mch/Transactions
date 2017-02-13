package com.liverm0r.transactions.dagger.currency.transactions;


import com.liverm0r.transactions.ui.transactions.TransactionsActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {TransactionModule.class})
@TransactionsScope
public interface TransactionsComponent {
    void inject(TransactionsActivity transactionsActivity);

    @Subcomponent.Builder
    interface Builder {
        TransactionsComponent.Builder transactionModule(TransactionModule module);
        TransactionsComponent build();
    }
}
