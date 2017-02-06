package com.liverm0r.transactions;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.liverm0r.transactions.dagger.application.AppComponent;
import com.liverm0r.transactions.dagger.application.AppModule;
import com.liverm0r.transactions.dagger.application.DaggerAppComponent;
import com.liverm0r.transactions.dagger.currency.CurrencyComponent;
import com.liverm0r.transactions.dagger.currency.CurrencyModule;
import com.liverm0r.transactions.dagger.currency.detail_transactions.DetailTransComponent;
import com.liverm0r.transactions.dagger.currency.detail_transactions.DetailTransModule;
import com.liverm0r.transactions.dagger.currency.transactions.TransactionModule;
import com.liverm0r.transactions.dagger.currency.transactions.TransactionsComponent;
import com.liverm0r.transactions.ui.transactions.ITransactionsRouter;

public class App extends Application {
    private static String TAG = App.class.getSimpleName();

    @SuppressWarnings("NullableProblems")
    @NonNull
    private AppComponent mAppComponent;

    private CurrencyComponent mCurrencyComponent;
    private TransactionsComponent mTransactionsComponent;
    private DetailTransComponent mDetailTransComponent;

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
    public TransactionsComponent getTransactionsComponent(ITransactionsRouter router) {
        if (mTransactionsComponent == null) {
            mTransactionsComponent = currencyComponent().plus(new TransactionModule(router));
        }
        return mTransactionsComponent;
    }

    public void removeTransactionsComponent() {
        mTransactionsComponent = null;
    }

    @NonNull
    public DetailTransComponent getDetailTransComponent() {
        if (mDetailTransComponent == null) {
            mDetailTransComponent = currencyComponent().plus(new DetailTransModule());
        }
        return mDetailTransComponent;
    }

    public void removeDetailTransactionsComponent() {
        mDetailTransComponent = null;
    }

    @NonNull
    private DaggerAppComponent.Builder prepareAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this));
    }
}
