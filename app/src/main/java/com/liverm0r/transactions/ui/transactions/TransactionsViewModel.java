package com.liverm0r.transactions.ui.transactions;


import android.support.annotation.NonNull;

import com.liverm0r.transactions.business.interactors.ITransactionsInteractor;
import com.liverm0r.transactions.common.error.IErrorHandler;
import com.liverm0r.transactions.common.rx_utils.RxSchedulersAbs;
import com.liverm0r.transactions.dagger.currency.transactions.TransactionsScope;
import com.liverm0r.transactions.ui.ui_base.BaseViewModelAbs;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

@TransactionsScope
public class TransactionsViewModel extends BaseViewModelAbs {

    private ITransactionsRouter mRouter;
    private ITransactionsInteractor mInteractor;

    private BehaviorSubject<List<TransactionsModel>> mTransactionsSubj;

    @Inject TransactionsViewModel(@NonNull RxSchedulersAbs rxSchedulers,
                                  @NonNull IErrorHandler errorHandler,
                                  @NonNull ITransactionsInteractor interactor,
                                  @NonNull ITransactionsRouter router) {
        super(rxSchedulers, errorHandler);
        this.mRouter = router;
        this.mInteractor = interactor;
        mTransactionsSubj = BehaviorSubject.create();
    }

    @NonNull Observable<List<TransactionsModel>> getTransactions() {

        if (mTransactionsSubj.getValue() == null) {
            bindAction(mInteractor.getTransactions().toObservable(), transactions -> {
                showProgress(false);
                mTransactionsSubj.onNext(transactions);
            });
        }
        return mTransactionsSubj;
    }

    void clickOnTransactions(@NonNull TransactionsModel transactionsModel) {
        mInteractor.skuChosen(transactionsModel.getSku());
        mRouter.showDetailProduct();
    }

}
