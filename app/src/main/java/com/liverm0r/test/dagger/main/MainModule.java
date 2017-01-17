package com.liverm0r.test.dagger.main;


import com.liverm0r.test.ui.main.IMainRouter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private IMainRouter mRouter;

    public MainModule(IMainRouter router) {
        mRouter = router;
    }

    @Provides
    @MainScope
    IMainRouter provideMainRouter(){
        return mRouter;
    }

    //IAivinApi provideApi() {return RetrofitUtils.createService(apiUrl, IAivinApi.class);}

}

