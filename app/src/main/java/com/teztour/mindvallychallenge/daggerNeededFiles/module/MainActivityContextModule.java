package com.teztour.mindvallychallenge.daggerNeededFiles.module;

import android.content.Context;

import com.teztour.mindvallychallenge.daggerNeededFiles.qualifer.ActivityContext;
import com.teztour.mindvallychallenge.daggerNeededFiles.scope.ActivityScope;
import com.teztour.mindvallychallenge.mainActivityMVP.MainActivity;

import dagger.Module;
import dagger.Provides;



@Module
public class MainActivityContextModule {


    private MainActivity mainActivity;
    private Context context;

    public MainActivityContextModule(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity(){
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context providesContext(){
        return context;
    }
}