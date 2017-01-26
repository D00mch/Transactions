package com.liverm0r.transactions.business.helpers.exchange.graph;


import android.support.annotation.NonNull;

public class CurrencyEdge {

    private final CurrencyNode source;
    private final CurrencyNode destination;
    private final float weight;
    private final float rate;

    public CurrencyEdge(@NonNull CurrencyNode source,
                        @NonNull CurrencyNode destination,
                        float rate) {

        this.source = source;
        this.destination = destination;
        this.rate = rate;
        this.weight = 1f / rate;
    }


    public @NonNull CurrencyNode getDestination() {
        return destination;
    }

    public @NonNull CurrencyNode getSource() {
        return source;
    }

    public float getWeight() {
        return weight;
    }

    public float getRate() {
        return this.rate;
    }

    @Override
    public String toString() {
        return "rate == " + rate + " weight == " + weight + " source == " + source + ", dest == " + destination + "\n";
    }
}
