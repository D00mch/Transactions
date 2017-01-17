package com.liverm0r.test.ui.common.ui_base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    abstract protected BaseViewModelAbs provideVM();

    private CompositeDisposable mDisposables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisposables = new CompositeDisposable();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        provideVM().onViewDestroyed();
        mDisposables.clear();
        baseViewModelActivity(provideVM());
    }

    protected void baseViewModelActivity(BaseViewModelAbs vm) {
        bind(vm.errorAction(), action -> action.accept(this));
        bind(vm.errorMessage(), System.out::println);
    }

    //—————————————————————————————————————————————————————————————————————— rx
    protected <T> void bind(Observable<T> observable, @NonNull Consumer<T> onNext) {
        mDisposables.add(
                observable.subscribe(onNext, e -> Log.e(TAG, "bind: error shouldn't come here"))
        );
    }
}
