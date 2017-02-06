package com.liverm0r.transactions.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.liverm0r.transactions.App;
import com.liverm0r.transactions.R;
import com.liverm0r.transactions.dagger.main.MainModule;
import com.liverm0r.transactions.ui.ui_base.BaseActivity;
import com.liverm0r.transactions.ui.ui_base.BaseViewModelAbs;
import com.liverm0r.transactions.ui.transactions.TransactionsActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.get(this).applicationComponent().plus(new MainModule(this)).inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override protected void onStart() {
        super.onStart();
        mViewModel.created();
    }


    //—————————————————————————————————————————————————————————————————————— routing

    @Override public void startTransactions() {
        startActivity(new Intent(this, TransactionsActivity.class));
        //finish();
    }
}
