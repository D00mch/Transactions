package com.liverm0r.test.ui.transactions;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.liverm0r.test.App;
import com.liverm0r.test.R;
import com.liverm0r.test.dagger.currency.transactions.TransactionModule;
import com.liverm0r.test.ui.common.ui_base.BaseActivity;
import com.liverm0r.test.ui.common.ui_base.BaseViewModelAbs;
import com.liverm0r.test.ui.detail_transactions.DetailActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class TransactionsActivity extends BaseActivity implements ITransactionsRouter {

    private static final String TAG = TransactionsActivity.class.getSimpleName();

    @Inject TransactionsViewModel mViewModel;

    @BindView(R.id.transactionRv) RecyclerView transactionRv;
    @BindView(R.id.toolbarTitle) TextView toolbarTitle;

    @Override protected BaseViewModelAbs provideVM() {
        return mViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.get(this).currencyComponent().plus(new TransactionModule(this)).inject(this);
        setContentView(R.layout.transaction_activity);
        ButterKnife.bind(this);

        toolbarTitle.setText(R.string.TransactionsTitle);
        bindViewModel(mViewModel);
    }

    private void bindViewModel(TransactionsViewModel vm) {
        Log.i(TAG, "bindViewModel");
        vm.getTransactions().subscribe(transactionsModels -> {
            Log.i(TAG, "bindViewModel: products == " + transactionsModels);
            setUpRv(transactionsModels);
        });
    }

    private void setUpRv(List<TransactionsModel> transactionsModels) {
        transactionRv.setLayoutManager(new LinearLayoutManager(this));
        OverScrollDecoratorHelper.setUpOverScroll(transactionRv, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        if (transactionRv.getAdapter() == null) {
            TransactionsAdapter transactionsAdapter = new TransactionsAdapter(
                    transactionsModel -> mViewModel.clickOnTransactions(transactionsModel));
            transactionsAdapter.setTransactionsModels(transactionsModels);
            transactionRv.setAdapter(transactionsAdapter);
        }
    }

    @Override public void showDetailProduct() {
        startActivity(new Intent(this, DetailActivity.class));
    }
}
