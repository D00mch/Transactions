package com.liverm0r.test.business.transactions.exchange;


import com.liverm0r.test.business.transactions.exchange.graph.GraphAlgorithm;
import com.liverm0r.test.data.model.Rate;

import java.util.List;

public class ExchangeImpByAlgorithm extends ExchangeImp {

    public ExchangeImpByAlgorithm(List<Rate> rates) {
        super(rates);
    }

    public ExchangeImpByAlgorithm(GraphAlgorithm algorithm) {
        mAlgorithm = algorithm;
    }

    public GraphAlgorithm getCurrentAlgorithm() {
        return mAlgorithm;
    }
}
