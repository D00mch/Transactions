package com.liverm0r.test.data.model;


import android.support.annotation.NonNull;

public class Transaction {

    private String amount;
    private String sku;
    private String currency;

    public Transaction(String amount, String sku, String currency) {
        this.amount = amount;
        this.sku = sku;
        this.currency = currency;
    }

    public @NonNull String getAmount() {
        return amount;
    }

    public @NonNull String getSku() {
        return sku;
    }

    public @NonNull String getCurrency() {
        return currency;
    }
}
