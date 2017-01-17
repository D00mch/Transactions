package com.liverm0r.test.business.currency.transactions.exchange;


import android.support.annotation.NonNull;

import com.liverm0r.test.business.currency.transactions.exchange.graph.CurrencyNode;
import com.liverm0r.test.business.currency.transactions.exchange.graph.GraphAlgorithm;
import com.liverm0r.test.business.currency.transactions.exchange.graph.RatesGraph;
import com.liverm0r.test.data.model.Rate;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ExchangeImp implements ICurrencyExchanger {

    GraphAlgorithm mAlgorithm;

    ExchangeImp() {
    }

    public ExchangeImp(List<Rate> rates) {
        RatesGraph graph = new RatesGraph(rates);
        mAlgorithm = new GraphAlgorithm(graph);
    }

    @Override public void calc(@NonNull CurrencyNode target) {
        mAlgorithm.calc(target);
    }

    @NonNull @Override public List<CurrencyNode> getPath(CurrencyNode source) {
        LinkedList<CurrencyNode> path = mAlgorithm.getPath(source);
        return path == null ? Collections.emptyList() : path;
    }

    @Override public float getFinalRate(@NonNull CurrencyNode source) {
        Float weight = mAlgorithm.getRates().get(source);
        return weight == null ? 1f : 1f / mAlgorithm.getRates().get(source);
    }
}
