package com.liverm0r.transactions.data.model;


public class DetailTransaction {
    private String from;
    private String to;
    private float sumFrom;
    private float sumTo;

    public DetailTransaction() {
    }

    public DetailTransaction(String from, String to, float sumFrom, float sumTo) {
        this.from = from;
        this.to = to;
        this.sumFrom = sumFrom;
        this.sumTo = sumTo;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public float getSumFrom() {
        return sumFrom;
    }

    public void setSumFrom(float sumFrom) {
        this.sumFrom = sumFrom;
    }

    public float getSumTo() {
        return sumTo;
    }

    public void setSumTo(float sumTo) {
        this.sumTo = sumTo;
    }
}
