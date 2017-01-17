package com.liverm0r.transactions.dagger.main;


import com.liverm0r.transactions.ui.main.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {MainModule.class})
@MainScope
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
