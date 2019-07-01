package com.teztour.mindvallychallenge.daggerNeededFiles.component;

import android.content.Context;

import com.teztour.mindvallychallenge.daggerNeededFiles.module.ContextModule;
import com.teztour.mindvallychallenge.daggerNeededFiles.qualifer.ApplicationContext;
import com.teztour.mindvallychallenge.daggerNeededFiles.scope.ApplicationScope;
import com.teztour.mindvallychallenge.root.MyApplication;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class})
public interface ApplicationComponent {


    @ApplicationContext
    Context getContext();

    void injectApplication(MyApplication application);
}