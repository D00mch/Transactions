package com.liverm0r.test.dagger.main;


import com.liverm0r.test.ui.main.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {MainModule.class})
@MainScope
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
