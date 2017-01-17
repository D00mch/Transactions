package com.liverm0r.test.business.transactions.exchange;

import com.liverm0r.test.business.transactions.exchange.graph.CurrencyNode;
import com.liverm0r.test.data.model.Rate;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExchangeImpTest {

    private static final List<Rate> RATES = new ArrayList<Rate>() {
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

    /*
        two ways to change CAD (target) to GBP(source)

        1.  cad —> aud == 1.26
            aud -> gbp == 1.5
                          1,890

        2.  cad -> usd == 0.97
            usd -> gbp == 0.8
                          0,776
     */

    private static final float CAD_TO_GBP_RATE = 1.89f;

    private static List<Rate> rates_cheap_AUD = new ArrayList<Rate>() {
        {
            addAll(RATES);
            add(new Rate("CAD", "1.26", "AUD"));
        }
    };

    @Test
    public void change_CAD_when_AUD_is_cheap() {
        change_CAD_to_GBP(rates_cheap_AUD, "CAD", "AUD", "GBP");
    }

    @Test
    public void change_CAD_when_USD_is_the_only_case() {
        change_CAD_to_GBP(RATES, "CAD", "USD", "GBP");
    }

    @Test
    public void final_rate_for_CAD_AUD_GBP() {
        ExchangeImp ex = makeExchangerFor("CAD", rates_cheap_AUD);
        CurrencyNode target = new CurrencyNode("GBP");
        System.out.println("ex.getFinalRate(source) == " + ex.getFinalRate(target));
        if(Float.compare(CAD_TO_GBP_RATE, ex.getFinalRate(target)) != 0){
            throw new AssertionError("final_rate_for_CAD_AUD_GBP should == " + CAD_TO_GBP_RATE);
        }
    }

    @Test
    public void final_rate_for_EUR_CAD_AUD_GBP() {

        /*
        exchange EUR (target) to GBP(source)
        eur -> cad == 0.5 * (CAD_TO_GBP_RATE = 1.89f) == 0,945
         */

        ExchangeImp ex = makeExchangerFor("EUR", rates_cheap_AUD);
        CurrencyNode target = new CurrencyNode("GBP");
        System.out.println("ex.getFinalRate(source) == " + ex.getFinalRate(target));
        if (Float.compare(0.945f, ex.getFinalRate(target)) != 0) {
            throw new AssertionError("final_rate_for_EUR_CAD_AUD_GBP should == " + 0.945f);
        }
    }

    @Test
    public void change_GBP_to_GBP() {
        ExchangeImp calculator = makeExchangerFor("GBP", RATES);
        CurrencyNode node = new CurrencyNode("GBP");
        System.out.println("calculator.getFinalRate(node) == " + calculator.getFinalRate(node));
        if (calculator.getFinalRate(node) != 1.0f) {
            throw new AssertionError("change_GBP_to_GBP should == " + 1.0f);
        }
    }

    private static void change_CAD_to_GBP(List<Rate> rates, String... resultNodes) {
        ExchangeImp calculator = makeExchangerFor("CAD", rates);
        CurrencyNode target = new CurrencyNode("GBP");
        List<CurrencyNode> path = calculator.getPath(target);

        System.out.println("\n path == " + path + ", rate == " + calculator.getFinalRate(target));

        ArrayList<CurrencyNode> currencyNodes = new ArrayList<>();
        for (String node : resultNodes) {
            currencyNodes.add(new CurrencyNode(node));
        }

        for (int i = 0; i < path.size(); i++) {
            if (!path.get(i).equals(currencyNodes.get(i))) {
                throw new AssertionError("expected nodes doesn't match with result");
            }
        }
    }

    private static ExchangeImp makeExchangerFor(String currencyName, List<Rate> rates) {
        ExchangeImp ex = new ExchangeImp(rates);
        CurrencyNode source = new CurrencyNode(currencyName);
        ex.calc(source);
        return ex;
    }
}