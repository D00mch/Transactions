package com.liverm0r.test.business.currency.transactions.exchange.graph;


import android.support.annotation.NonNull;

public class CurrencyNode {

    final private String mCurrencyName;

    public CurrencyNode(@NonNull String name) {
        this.mCurrencyName = name;
    }


    //—————————————————————————————————————————————————————————————————————— Object's methods (based on name);

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyNode node = (CurrencyNode) o;
        return mCurrencyName.equals(node.mCurrencyName);
    }

    @Override public int hashCode() {
        return mCurrencyName.hashCode();
    }

    @Override
    public String toString() {
        return mCurrencyName;
    }

}
