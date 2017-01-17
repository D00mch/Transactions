package com.liverm0r.test.dagger.application;


import com.liverm0r.test.dagger.currency.CurrencyComponent;
import com.liverm0r.test.dagger.currency.CurrencyModule;
import com.liverm0r.test.dagger.main.MainComponent;
import com.liverm0r.test.dagger.main.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, UtilsModule.class})
public interface AppComponent {

    MainComponent plus(MainModule mainModule);
    CurrencyComponent plus(CurrencyModule currencyModule);

}
