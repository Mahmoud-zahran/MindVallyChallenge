package com.teztour.mindvallychallenge.root;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.teztour.mindvallychallenge.daggerNeededFiles.component.ApplicationComponent;
import com.teztour.mindvallychallenge.daggerNeededFiles.component.DaggerApplicationComponent;
import com.teztour.mindvallychallenge.daggerNeededFiles.module.ContextModule;


public class MyApplication extends Application {
    ApplicationComponent applicationComponent;
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        dependencyInjection();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static MyApplication get(Activity activity) {

      //  return getApplicationContext();
          return (MyApplication) activity.getApplication();
    }
    public static Context getContext(){
        return mContext;
    }

    private void dependencyInjection(){
        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);
    }
}