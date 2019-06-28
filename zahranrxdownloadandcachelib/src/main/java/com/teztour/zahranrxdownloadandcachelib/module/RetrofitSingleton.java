package com.teztour.zahranrxdownloadandcachelib.module;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teztour.zahranrxdownloadandcachelib.ApiUrls;
import com.teztour.zahranrxdownloadandcachelib.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Mahmoud Zahran on 26/06/2019.
 *  This module handles creation of Retrofit Instance and
 *  allows for interception of calls, using HttpLoggingInterceptor.
 *
 */
public class RetrofitSingleton {


    private static Retrofit mJsonInstance;
    private static Retrofit mXmlInstance;
    private static Retrofit mInstance;
    private static OkHttpClient.Builder okHttpClientBuilder;
    private static HttpLoggingInterceptor loggingInterceptor;


    public static  Retrofit getInstanceJson() {
       if (mJsonInstance == null) {


            okHttpClientBuilder = new OkHttpClient.Builder();/// I must use OkHttpClient.Builder to add the log interceptor to the request
            okHttpClientBuilder.connectTimeout(150, TimeUnit.SECONDS);
            okHttpClientBuilder.readTimeout(150, TimeUnit.SECONDS);
            okHttpClientBuilder.writeTimeout(150, TimeUnit.SECONDS);
            okHttpClientBuilder.retryOnConnectionFailure(true);
            loggingInterceptor = new HttpLoggingInterceptor(); /// I must use HttpLoggingInterceptor to could identify log configuration
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); /// add the log level body, header or ... etc

            if(BuildConfig.DEBUG){
                // only enable log in depug mode to still secure my requests like password ..
                okHttpClientBuilder.addInterceptor(loggingInterceptor);
            }
            try{
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
            mJsonInstance = new Retrofit.Builder()
                    .baseUrl(ApiUrls.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //.addConverterFactory(ScalarsConverterFactory.create())
                    //.addConverterFactory(SimpleXmlConverterFactory.create())
                    .client(okHttpClientBuilder.build())
                    .build();
            } catch(Exception e) {

                    Log.i("retroSingConvFactcheck", "getInstance: "+e.getMessage());
            }
        }

        return mJsonInstance;
    }
    public static  Retrofit getInstanceString() {
        if (mInstance == null) {


            okHttpClientBuilder = new OkHttpClient.Builder();  /// I must use OkHttpClient.Builder to add the log interceptor to the request
            okHttpClientBuilder.connectTimeout(150, TimeUnit.SECONDS);
            okHttpClientBuilder.readTimeout(150, TimeUnit.SECONDS);
            okHttpClientBuilder.writeTimeout(150, TimeUnit.SECONDS);
            okHttpClientBuilder.retryOnConnectionFailure(true);
            loggingInterceptor = new HttpLoggingInterceptor(); /// I must use HttpLoggingInterceptor to could identify log configuration
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); /// add the log level body, header or ... etc

            if(BuildConfig.DEBUG){
                // only enable log in depug mode to still secure my requests like password ..
                okHttpClientBuilder.addInterceptor(loggingInterceptor);
            }
            try{
                mInstance = new Retrofit.Builder()
                        .baseUrl(ApiUrls.BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        // .addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                        //.addConverterFactory(SimpleXmlConverterFactory.create())
                        .client(okHttpClientBuilder.build())
                        .build();
            } catch(Exception e) {
                Log.i("retroSingConvFactcheck", "getInstance: "+e.getMessage());
            }
        }

        return mInstance;
    }
    public static  Retrofit getInstanceXml() {
        if (mXmlInstance == null) {


            okHttpClientBuilder = new OkHttpClient.Builder();  /// I must use OkHttpClient.Builder to add the log interceptor to the request
            okHttpClientBuilder.connectTimeout(150, TimeUnit.SECONDS);
            okHttpClientBuilder.readTimeout(150, TimeUnit.SECONDS);
            okHttpClientBuilder.writeTimeout(150, TimeUnit.SECONDS);
            okHttpClientBuilder.retryOnConnectionFailure(true);
            loggingInterceptor = new HttpLoggingInterceptor(); /// I must use HttpLoggingInterceptor to could identify log configuration
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); /// add the log level body, header or ... etc

            if(BuildConfig.DEBUG){
                // only enable log in depug mode to still secure my requests like password ..
                okHttpClientBuilder.addInterceptor(loggingInterceptor);
            }
            try{
                mXmlInstance = new Retrofit.Builder()
                        .baseUrl(ApiUrls.BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        // .addConverterFactory(GsonConverterFactory.create())
                       // .addConverterFactory(ScalarsConverterFactory.create())

                        .addConverterFactory(SimpleXmlConverterFactory.create())
                        .client(okHttpClientBuilder.build())
                        .build();
            } catch(Exception e) {
                Log.i("retroSingConvFactcheck", "getInstance: "+e.getMessage());
            }
        }

        return mXmlInstance;
    }
}
