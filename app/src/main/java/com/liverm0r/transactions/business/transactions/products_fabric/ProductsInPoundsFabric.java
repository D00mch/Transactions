package com.liverm0r.transactions.business.transactions.products_fabric;


import android.support.annotation.NonNull;

import com.liverm0r.transactions.business.transactions.exchange.ExchangeImp;
import com.liverm0r.transactions.business.transactions.exchange.ICurrencyExchanger;
import com.liverm0r.transactions.business.transactions.exchange.graph.CurrencyNode;
import com.liverm0r.transactions.data.model.DetailTransaction;
import com.liverm0r.transactions.data.model.Product;
import com.liverm0r.transactions.data.model.Rate;
import com.liverm0r.transactions.data.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.liverm0r.transactions.common.NAMES.GBP;

public class ProductsInPoundsFabric implements IProductsFabric {

    private static final String TAG = ProductsInPoundsFabric.class.getSimpleName();

    private ICurrencyExchanger mExchanger;
    private HashMap<String, Float> currencyToRateMap; // caching already calculated rates

    @NonNull @Override
    public List<Product> get(@NonNull List<Transaction> transactions,
                             @NonNull List<Rate> rates) {

        currencyToRateMap = new HashMap<>();
        mExchanger = new ExchangeImp(rates);

        HashMap<String, Product> skuToProduct = new HashMap<>();

        for (Transaction transaction : transactions) {
            Product product = skuToProduct.get(transaction.getSku());
            if (product == null) {
                product = new Product();
                product.setSku(transaction.getSku());
                skuToProduct.put(transaction.getSku(), product);
                product.setTransactions(new LinkedList<>());
            }
            DetailTransaction transInPounds = buildNewTransaction(transaction);
            product.getTransactions().add(transInPounds);
        }

        return new ArrayList<>(skuToProduct.values());
    }

    private DetailTransaction buildNewTransaction(Transaction prevTrans) {

        Float currencyRate = currencyToRateMap.get(prevTrans.getCurrency());
        if (currencyRate == null) {
            mExchanger.calc(new CurrencyNode(prevTrans.getCurrency()));
            currencyRate = mExchanger.getFinalRate(new CurrencyNode(GBP));
            currencyToRateMap.put(prevTrans.getCurrency(), currencyRate);
        }
        float prevAmount = Float.parseFloat(prevTrans.getAmount());
        float newAmount = currencyRate * prevAmount;

        return new DetailTransaction(prevTrans.getCurrency(), GBP, prevAmount, newAmount);
    }
}
