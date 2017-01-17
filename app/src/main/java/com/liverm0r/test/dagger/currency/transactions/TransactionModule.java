package com.liverm0r.test.dagger.currency.transactions;


import com.liverm0r.test.ui.transactions.ITransactionsRouter;

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
