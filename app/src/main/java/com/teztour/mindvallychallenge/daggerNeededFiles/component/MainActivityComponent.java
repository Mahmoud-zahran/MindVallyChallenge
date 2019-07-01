package com.teztour.mindvallychallenge.daggerNeededFiles.component;


import android.content.Context;

import com.teztour.mindvallychallenge.daggerNeededFiles.module.MainActivityContextModule;
import com.teztour.mindvallychallenge.daggerNeededFiles.module.MainActivityMvpModule;
import com.teztour.mindvallychallenge.daggerNeededFiles.qualifer.ActivityContext;
import com.teztour.mindvallychallenge.mainActivityMVP.MainActivity;
import com.teztour.mindvallychallenge.daggerNeededFiles.scope.ActivityScope;
import dagger.Component;

@ActivityScope
@Component(modules = {MainActivityContextModule.class, MainActivityMvpModule.class},
        dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    @ActivityContext
    Context getContext();
    void injectMainActivity(MainActivity mainActivity);
}