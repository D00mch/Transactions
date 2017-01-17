package com.liverm0r.test.business.currency;


import com.liverm0r.test.data.model.Rate;
import com.liverm0r.test.data.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public abstract class CurrencyData {
    public static final List<Rate> sRates = new ArrayList<Rate>() {
        {
            add(new Rate("GBP", "1.2", "USD"));
            add(new Rate("GBP", "0.79", "AUD"));
            add(new Rate("USD", "1.1", "CAD"));
            add(new Rate("USD", "0.80", "GBP"));
            add(new Rate("CAD", "0.97", "USD"));
            add(new Rate("AUD", "1.5", "GBP"));
            add(new Rate("EUR", "0.5", "CAD"));
        }
    };

    public static final ArrayList<Transaction> sTransactions = new ArrayList<Transaction>() {
        {
            add(new Transaction("15", "J4064", "GBP"));
            add(new Transaction("2", "J4064", "EUR"));
            add(new Transaction("12", "J4064", "USD"));
            add(new Transaction("31", "J4064", "AUD"));
            add(new Transaction("4", "distinguish", "CAD"));
        }
    };

}
