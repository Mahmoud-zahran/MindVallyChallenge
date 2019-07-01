package com.teztour.mindvallychallenge.daggerNeededFiles.module;


import android.content.Context;

import com.teztour.mindvallychallenge.daggerNeededFiles.qualifer.ApplicationContext;
import com.teztour.mindvallychallenge.daggerNeededFiles.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context providesContext(){
        return context;
    }
}