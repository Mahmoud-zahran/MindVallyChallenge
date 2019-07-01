package com.teztour.mindvallychallenge.daggerNeededFiles.module;


import com.teztour.mindvallychallenge.daggerNeededFiles.scope.ActivityScope;
import com.teztour.mindvallychallenge.mainActivityMVP.MainActivityContract;
import com.teztour.mindvallychallenge.mainActivityMVP.MainActivityPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityMvpModule {

    private MainActivityContract.View mView;

    public MainActivityMvpModule(MainActivityContract.View mView){
        this.mView = mView;
    }

    @Provides
    @ActivityScope
    MainActivityContract.View provideView(){
        return mView;
    }

    @Provides
    @ActivityScope
    MainActivityPresenterImpl providePresenter( MainActivityContract.View mView){
        return new MainActivityPresenterImpl( mView);
    }

}
