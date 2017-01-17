package com.liverm0r.transactions.ui.transactions;


public class TransactionsModel {
    private String id;
    private int amount;
    private String description;

    public TransactionsModel(String id, int amount, String description) {
        this.id = id;
        this.amount = amount;
        this.description = description;
    }

    public String getSku() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}

