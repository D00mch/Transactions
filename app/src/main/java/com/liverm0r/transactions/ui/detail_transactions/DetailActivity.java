package com.liverm0r.transactions.ui.detail_transactions;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.liverm0r.transactions.App;
import com.liverm0r.transactions.R;
import com.liverm0r.transactions.ui.ui_base.BaseActivity;
import com.liverm0r.transactions.ui.ui_base.BaseViewModelAbs;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.detailTransRv) RecyclerView detailTransRv;
    @BindView(R.id.detailTransTotalText) TextView detailTransTotalText;
    @BindView(R.id.toolbarTitle) TextView toolbarTitle;

    @Inject DetailViewModel mViewModel;

    private DetailTransAdapter mDetailTransAdapter;

    @Override protected BaseViewModelAbs provideVM() {
        return mViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.get(this).getDetailTransComponent().inject(this);
        setContentView(R.layout.detail_trans_activity);
        ButterKnife.bind(this);

        setUpRv();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if(isFinishing()){
            App.get(this).removeDetailTransactionsComponent();
        }
    }

    @Override protected void onStart() {
        super.onStart();
        setUpViewModel(mViewModel);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void setUpViewModel(DetailViewModel vm) {
        bind(vm.getModel(), model -> {
            mDetailTransAdapter.setTransactionsModels(model.getTransactions());
            detailTransTotalText.setText(String.format("Total: £ %.2f", model.getTotal()));
            toolbarTitle.setText("Transactions for " + model.getSku());
        });
    }

    private void setUpRv() {
        detailTransRv.setLayoutManager(new LinearLayoutManager(this));
        OverScrollDecoratorHelper.setUpOverScroll(detailTransRv, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        mDetailTransAdapter = new DetailTransAdapter();
        detailTransRv.setAdapter(mDetailTransAdapter);
    }
}
