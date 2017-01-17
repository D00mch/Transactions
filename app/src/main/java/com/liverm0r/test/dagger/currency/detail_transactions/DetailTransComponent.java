package com.liverm0r.test.dagger.currency.detail_transactions;


import com.liverm0r.test.ui.detail_transactions.DetailActivity;

import dagger.Subcomponent;

@DetailTransScope
@Subcomponent(modules = {DetailTransModule.class})
public interface DetailTransComponent {
    void inject(DetailActivity detailActivity);
}
