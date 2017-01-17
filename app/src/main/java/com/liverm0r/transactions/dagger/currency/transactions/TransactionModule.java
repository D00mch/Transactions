package com.liverm0r.transactions.dagger.currency.transactions;


import com.liverm0r.transactions.ui.transactions.ITransactionsRouter;

import dagger.Module;
import dagger.Provides;

@Module
public class TransactionModule {
    private ITransactionsRouter mRouter;

    public TransactionModule(ITransactionsRouter router) {
        mRouter = router;
    }

    @Provides
    @TransactionsScope
    ITransactionsRouter provideTransactionRouter() {
        return mRouter;
    }
}
