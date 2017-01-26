package com.liverm0r.transactions.ui.detail_transactions;


import android.support.annotation.NonNull;

import com.liverm0r.transactions.business.interactors.IDetailTransInteractor;
import com.liverm0r.transactions.common.rx_utils.RxSchedulersAbs;
import com.liverm0r.transactions.dagger.currency.detail_transactions.DetailTransScope;
import com.liverm0r.transactions.common.error.IErrorHandler;
import com.liverm0r.transactions.ui.ui_base.BaseViewModelAbs;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

@DetailTransScope
public class DetailViewModel extends BaseViewModelAbs {

    private IDetailTransInteractor mInteractor;
    private BehaviorSubject<DetailTransModel> mModelSubject;

    @Inject
    DetailViewModel(@NonNull RxSchedulersAbs rxSchedulers,
                           @NonNull IErrorHandler errorHandler,
                           @NonNull IDetailTransInteractor interactor) {
        super(rxSchedulers, errorHandler);
        mInteractor = interactor;
        mModelSubject = BehaviorSubject.create();
    }

    @NonNull Observable<DetailTransModel> getModel() {
        if (mModelSubject.getValue() == null) {
            bindAction(mInteractor.getModel().toObservable(), model -> {
                showProgress(false);
                mModelSubject.onNext(model);
            });
        }
        return mModelSubject;
    }
}
