package com.liverm0r.test.business.detail_transactions;


import com.liverm0r.test.ui.detail_transactions.DetailTransModel;

import io.reactivex.Single;

public interface IDetailTransInteractor {
    Single<DetailTransModel> getModel();
}
