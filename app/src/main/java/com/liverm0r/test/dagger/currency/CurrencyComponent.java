package com.liverm0r.test.dagger.currency;


import com.liverm0r.test.dagger.currency.detail_transactions.DetailTransComponent;
import com.liverm0r.test.dagger.currency.detail_transactions.DetailTransModule;
import com.liverm0r.test.dagger.currency.transactions.TransactionModule;
import com.liverm0r.test.dagger.currency.transactions.TransactionsComponent;

import dagger.Subcomponent;

@Subcomponent(modules = {CurrencyModule.class})
@CurrencyScope
public interface CurrencyComponent {

    TransactionsComponent plus(TransactionModule transactionModule);

    DetailTransComponent plus(DetailTransModule detailTransModule);
}
