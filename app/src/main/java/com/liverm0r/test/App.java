package com.liverm0r.test;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.liverm0r.test.dagger.application.AppComponent;
import com.liverm0r.test.dagger.application.AppModule;
import com.liverm0r.test.dagger.application.DaggerAppComponent;
import com.liverm0r.test.dagger.currency.CurrencyComponent;
import com.liverm0r.test.dagger.currency.CurrencyModule;

public class App extends Application {
    private static String TAG = App.class.getSimpleName();

    @SuppressWarnings("NullableProblems")
    @NonNull
    private AppComponent mAppComponent;

    @SuppressWarnings("NullableProblems")
    private CurrencyComponent mCurrencyComponent;

    @NonNull
    public static App get(@NonNull Context context) {
        return (App) context.getApplicationContext();
    }

    @Override public void onCreate() {
        super.onCreate();
        mAppComponent = prepareAppComponent().build();
    }

    @NonNull
    public AppComponent applicationComponent() {
        return mAppComponent;
    }

    @NonNull
    public CurrencyComponent currencyComponent() {
        if (mCurrencyComponent == null) {
            mCurrencyComponent = applicationComponent().plus(new CurrencyModule());
        }
        return mCurrencyComponent;
    }

    public void removeCurrencyComponentFromCache() {
        mCurrencyComponent = null;
    }

    @NonNull
    private DaggerAppComponent.Builder prepareAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this));
    }
}
