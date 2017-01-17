package com.liverm0r.transactions.business.detail_transactions;


import com.liverm0r.transactions.ui.detail_transactions.DetailTransModel;

import io.reactivex.Single;

public interface IDetailTransInteractor {
    Single<DetailTransModel> getModel();
}
