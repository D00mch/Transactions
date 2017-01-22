package com.liverm0r.transactions.ui.transactions;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liverm0r.transactions.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


 class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {

    private List<TransactionsModel> mTransactionsModels;
    private IAction mAction;

    interface IAction{
        void onClick(TransactionsModel model);
    }


     TransactionsAdapter(IAction actionOnPress) {
        mAction = actionOnPress;
        mTransactionsModels = new ArrayList<>();
    }


     void setTransactionsModels(List<TransactionsModel> transactionsModels) {
        mTransactionsModels = transactionsModels;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transactions_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TransactionsModel transactionsModel = mTransactionsModels.get(position);
        holder.textSku.setText(transactionsModel.getSku());
        holder.textAmount.setText(String.valueOf(transactionsModel.getAmount()));
        holder.parentView.setOnClickListener(v -> mAction.onClick(transactionsModel));
    }

    @Override
    public int getItemCount() {
        return mTransactionsModels.size();
    }


     static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.transactionRvSku) TextView textSku;
        @BindView(R.id.transactionRvAmount) TextView textAmount;
        @BindView(R.id.transactionRvItemRoot) ViewGroup parentView;


         ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
