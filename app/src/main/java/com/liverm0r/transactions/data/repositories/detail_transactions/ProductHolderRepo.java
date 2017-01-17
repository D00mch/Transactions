package com.liverm0r.transactions.data.repositories.detail_transactions;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.liverm0r.transactions.data.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductHolderRepo implements IProductHolderRepo {

    private List<Product> mProducts;
    private String mSku;

    public ProductHolderRepo() {
        mProducts = new ArrayList<>();
    }

    @Override public void setProducts(@NonNull List<Product> products) {
        mProducts = products;
    }

    @Override public @NonNull List<Product> getProducts() {
        return mProducts;
    }


    @Override public void setCurrentSku(@NonNull String sku) {
        mSku = sku;
    }

    @Nullable @Override public String getCurrentSku() {
        return mSku;
    }
}
