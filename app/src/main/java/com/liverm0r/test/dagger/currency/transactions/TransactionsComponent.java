package com.liverm0r.test.dagger.currency.transactions;


import com.liverm0r.test.ui.transactions.TransactionsActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {TransactionModule.class})
@TransactionsScope
public interface TransactionsComponent {
    void inject(TransactionsActivity transactionsActivity);
}
