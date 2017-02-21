package com.liverm0r.transactions.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.liverm0r.transactions.App;
import com.liverm0r.transactions.R;
import com.liverm0r.transactions.dagger.main.MainModule;
import com.liverm0r.transactions.presentation.transactions.TransactionsActivity;
import com.liverm0r.transactions.presentation.ui_base.BaseActivity;
import com.liverm0r.transactions.presentation.ui_base.BaseViewModelAbs;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IMainRouter {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject MainViewModel mViewModel;

    @BindView(R.id.testTextView) TextView testTextView;

    @Override protected BaseViewModelAbs provideVM() {
        return mViewModel;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.get(this)
                .applicationComponent()
                .mainComponentBuilder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mViewModel.created();
    }

    //—————————————————————————————————————————————————————————————————————— routing

    @Override public void startTransactions() {
        startActivity(new Intent(this, TransactionsActivity.class));
        //finish();
    }
}
