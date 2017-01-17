package com.liverm0r.transactions.ui.detail_transactions;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.liverm0r.transactions.App;
import com.liverm0r.transactions.R;
import com.liverm0r.transactions.dagger.currency.detail_transactions.DetailTransModule;
import com.liverm0r.transactions.data.model.DetailTransaction;
import com.liverm0r.transactions.ui.common.ui_base.BaseActivity;
import com.liverm0r.transactions.ui.common.ui_base.BaseViewModelAbs;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.detailTransRv) RecyclerView detailTransRv;
    @BindView(R.id.detailTransTotalText) TextView detailTransTotalText;
    @BindView(R.id.toolbarTitle) TextView toolbarTitle;

    @Inject DetailViewModel mViewModel;

    @Override protected BaseViewModelAbs provideVM() {
        return mViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.get(this).currencyComponent().plus(new DetailTransModule()).inject(this);
        setContentView(R.layout.detail_trans_activity);
        ButterKnife.bind(this);
        setUpViewModel(mViewModel);
    }

    private void setUpViewModel(DetailViewModel vm) {
        bind(vm.getModel(), m -> {
            setUpRv(m.getTransactions());
            detailTransTotalText.setText(String.format("Total: £ %.2f", m.getTotal()));
            toolbarTitle.setText("Transactions for " + m.getSku());
        });
    }

    private void setUpRv(List<DetailTransaction> transactions) {

        detailTransRv.setLayoutManager(new LinearLayoutManager(this));
        OverScrollDecoratorHelper.setUpOverScroll(detailTransRv, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        if (detailTransRv.getAdapter() == null) {
            DetailTransAdapter detailTransAdapter = new DetailTransAdapter();
            detailTransRv.setAdapter(detailTransAdapter);
            detailTransAdapter.setTransactionsModels(transactions);
        }
    }
}
