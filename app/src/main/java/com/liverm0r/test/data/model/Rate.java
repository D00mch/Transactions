package com.liverm0r.test.data.model;


public class Rate {
    private String from;
    private String to;
    private String rate;

    public Rate(String from, String rate, String to) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getRate() {
        return rate;
    }

    public float getFloatRate(){
        try {
            return Float.parseFloat(rate);
        } catch (NumberFormatException ex){
            return 0.0f;
        }
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override public String toString() {
        return "from " + from + " to " + to;
    }
}
