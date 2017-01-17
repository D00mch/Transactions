package com.liverm0r.test.business.currency.detail_transactions;


import android.util.Log;

import com.liverm0r.test.data.model.Product;
import com.liverm0r.test.data.repositories.detail_transactions.IProductHolderRepo;
import com.liverm0r.test.ui.detail_transactions.DetailTransModel;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

import io.reactivex.Single;

public class DetailTransInteractor implements IDetailTransInteractor {

    private static final String TAG = DetailTransInteractor.class.getSimpleName();

    private IProductHolderRepo mProductHolderRepo;

    public DetailTransInteractor(IProductHolderRepo productHolderRepo) {
        mProductHolderRepo = productHolderRepo;
    }

    @Override public Single<DetailTransModel> getModel() {
        return Single.create(source -> {

            List<Product> products = mProductHolderRepo.getProducts();

            try {
                DetailTransModel model = buildDetailTransactionModel(products, mProductHolderRepo.getCurrentSku());
                source.onSuccess(model);
            } catch (EmptyCacheIOException e) {
                Log.e(TAG, "getModel: exception == " + e);
                source.onError(e);
            }
        });
    }

    private DetailTransModel buildDetailTransactionModel(List<Product> products,
                                                         String sku) throws EmptyCacheIOException {
        if (products.isEmpty() || sku == null) throw new EmptyCacheIOException();
        Product prod = null;
        for (Product product : products) {
            if (product.getSku().equals(sku)) {
                prod = product;
                break;
            }
        }
        if (prod == null) throw new IOError(new IOException("Wrong sku passed"));
        return new DetailTransModel(sku, prod.getTotal(), prod.getTransactions());
    }
}
