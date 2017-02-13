package com.liverm0r.transactions.dagger.currency.detail_transactions;


import com.liverm0r.transactions.ui.detail_transactions.DetailActivity;

import dagger.Subcomponent;

@DetailTransScope
@Subcomponent(modules = {DetailTransModule.class})
public interface DetailTransComponent {
    void inject(DetailActivity detailActivity);

    @Subcomponent.Builder
    interface Builder {
        DetailTransComponent.Builder detailTransModule(DetailTransModule module);
        DetailTransComponent build();
    }
}
