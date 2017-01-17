package com.liverm0r.transactions.business.transactions.products_fabric;

import com.liverm0r.transactions.business.currency.CurrencyData;
import com.liverm0r.transactions.data.model.Product;
import com.liverm0r.transactions.data.model.Rate;
import com.liverm0r.transactions.data.model.Transaction;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProductsPoundsFabricTest {

    private List<Rate> mRates = CurrencyData.sRates;
    private List<Transaction> mTransactions = CurrencyData.sTransactions;

    private ProductsInPoundsFabric mProductsPoundsFabric;

    @Before
    public void setUp() throws Exception {
        mProductsPoundsFabric = new ProductsInPoundsFabric();

    }

    @Test public void name() throws Exception {

        List<Product> products = mProductsPoundsFabric.get(mTransactions, mRates);
        for (Product product : products) System.out.println(product);

        if (products.size() != 2) {
            throw new AssertionError("we have only two type of sku in test data");
        }
    }
}