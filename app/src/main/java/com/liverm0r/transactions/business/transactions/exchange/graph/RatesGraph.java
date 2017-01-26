package com.liverm0r.transactions.business.transactions.exchange.graph;


import android.support.annotation.NonNull;

import com.liverm0r.transactions.data.model.Rate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RatesGraph {
    private List<CurrencyNode> nodes;
    private List<CurrencyEdge> edges;

    public RatesGraph(@NonNull List<Rate> rates) {
        Set<String> currencyNames = createCurrencySet(rates);
        nodes = new ArrayList<>(currencyNames.size());
        edges = new ArrayList<>(rates.size());

        for (String currencyName : currencyNames) {
            nodes.add(new CurrencyNode(currencyName));
        }

        for (Rate rate : rates) {
            CurrencyEdge edge = new CurrencyEdge(
                    new CurrencyNode(rate.getFrom()),
                    new CurrencyNode(rate.getTo()), rate.getFloatRate());
            edges.add(edge);
        }

        System.out.println("nodes == " + nodes);
        System.out.println("edges == " + edges);
    }


    private @NonNull Set<String> createCurrencySet(@NonNull List<Rate> rates) {
        HashSet<String> currencyNamesSet = new HashSet<>();
        for (Rate rate : rates) {
            currencyNamesSet.add(rate.getFrom());
            currencyNamesSet.add(rate.getTo());
        }
        return currencyNamesSet;
    }

    @NonNull List<CurrencyNode> getVertexes() {
        return nodes;
    }

    @NonNull List<CurrencyEdge> getEdges() {
        return edges;
    }
}
