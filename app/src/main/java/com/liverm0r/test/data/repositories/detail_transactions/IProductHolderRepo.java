package com.liverm0r.test.data.repositories.detail_transactions;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.liverm0r.test.data.model.Product;

import java.util.List;

public interface IProductHolderRepo {

    void setProducts(@NonNull List<Product> products);

    void setCurrentSku(@NonNull String sku);

    @NonNull List<Product> getProducts();

    @Nullable String getCurrentSku();
}
