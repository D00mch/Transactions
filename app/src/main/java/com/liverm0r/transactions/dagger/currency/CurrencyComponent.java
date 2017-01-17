package com.liverm0r.transactions.dagger.currency;


import com.liverm0r.transactions.dagger.currency.detail_transactions.DetailTransComponent;
import com.liverm0r.transactions.dagger.currency.detail_transactions.DetailTransModule;
import com.liverm0r.transactions.dagger.currency.transactions.TransactionModule;
import com.liverm0r.transactions.dagger.currency.transactions.TransactionsComponent;

import dagger.Subcomponent;

@Subcomponent(modules = {CurrencyModule.class})
@CurrencyScope
public interface CurrencyComponent {

    TransactionsComponent plus(TransactionModule transactionModule);

    DetailTransComponent plus(DetailTransModule detailTransModule);
}
