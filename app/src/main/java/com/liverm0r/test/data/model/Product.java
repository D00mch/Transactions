package com.liverm0r.test.data.model;


import java.util.List;

public class Product {
    private String sku;
    private double total;
    List<DetailTransaction> transactions;

    public Product() {
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getTotal() {
        if (total == 0.0) {
            for (DetailTransaction transaction : transactions) {
                total += transaction.getSumTo();
            }
        }
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetailTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<DetailTransaction> transactions) {
        this.transactions = transactions;
    }

    @Override public String toString() {
        return "sku: " + sku + " total = " + getTotal() + " transactions.count == " + transactions.size();
    }
}
