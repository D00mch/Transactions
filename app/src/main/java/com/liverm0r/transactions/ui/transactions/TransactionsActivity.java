package com.liverm0r.transactions.ui.transactions;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.liverm0r.transactions.App;
import com.liverm0r.transactions.R;
import com.liverm0r.transactions.ui.detail_transactions.DetailActivity;
import com.liverm0r.transactions.ui.ui_base.BaseActivity;
import com.liverm0r.transactions.ui.ui_base.BaseViewModelAbs;

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

    private TransactionsAdapter mTransactionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.get(this).getTransactionsComponent(this).inject(this);
        setContentView(R.layout.transaction_activity);
        ButterKnife.bind(this);

        toolbarTitle.setText(R.string.TransactionsTitle);
        setUpRv();
        bindViewModel(mViewModel);
    }

    private void setUpRv() {
        mTransactionsAdapter = new TransactionsAdapter(
                transactionsModel -> mViewModel.clickOnTransactions(transactionsModel));
        transactionRv.setLayoutManager(new LinearLayoutManager(this));
        OverScrollDecoratorHelper.setUpOverScroll(transactionRv, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        transactionRv.setAdapter(mTransactionsAdapter);
    }

    private void bindViewModel(TransactionsViewModel vm) {
        vm.getTransactions().subscribe(transactionsModels -> {
            mTransactionsAdapter.setTransactionsModels(transactionsModels);
        });
    }

    @Override public void showDetailProduct() {
        startActivity(new Intent(this, DetailActivity.class));
    }
}
