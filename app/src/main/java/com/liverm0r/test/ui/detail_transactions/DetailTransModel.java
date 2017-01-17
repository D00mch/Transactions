package com.liverm0r.test.ui.detail_transactions;


import android.support.annotation.NonNull;

import com.liverm0r.test.data.model.DetailTransaction;

import java.util.List;

public class DetailTransModel {
    private String sku;
    private double total;
    private List<DetailTransaction> transactions;


    public DetailTransModel(@NonNull String sku,
                            double total,
                            @NonNull List<DetailTransaction> transactions) {
        this.sku = sku;
        this.total = total;
        this.transactions = transactions;
    }

    public @NonNull String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public @NonNull List<DetailTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<DetailTransaction> transactions) {
        this.transactions = transactions;
    }
}
