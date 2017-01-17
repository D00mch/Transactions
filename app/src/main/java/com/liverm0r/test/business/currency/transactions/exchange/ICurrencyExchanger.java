package com.liverm0r.test.business.currency.transactions.exchange;


import android.support.annotation.NonNull;

import com.liverm0r.test.business.currency.transactions.exchange.graph.CurrencyNode;

import java.util.List;

/**
 * Use calc() for every new target CurrencyNode,
 * before you try to getPath() and getFinalRate()
 */
public interface ICurrencyExchanger {

    void calc(@NonNull CurrencyNode target);

    @NonNull
    List<CurrencyNode> getPath(CurrencyNode source);

    // return 1.0f if you try to change some CurrencyNode to the same CurrencyNode
    float getFinalRate(@NonNull CurrencyNode source);
}
