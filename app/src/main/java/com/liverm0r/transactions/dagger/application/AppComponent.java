package com.liverm0r.transactions.dagger.application;


import com.liverm0r.transactions.dagger.currency.CurrencyComponent;
import com.liverm0r.transactions.dagger.main.MainComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, UtilsModule.class})
public interface AppComponent {

    MainComponent.Builder mainComponentBuilder();
    CurrencyComponent.Builder currencyComponentBuilder();
}
