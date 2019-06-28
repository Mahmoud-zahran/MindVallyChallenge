package com.teztour.zahranrxdownloadandcachelib.utils;


import com.teztour.zahranrxdownloadandcachelib.ApiService;
import com.teztour.zahranrxdownloadandcachelib.interfaces.IMProvider;
import com.teztour.zahranrxdownloadandcachelib.models.MDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.module.RetrofitSingleton;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Mahmoud Zahran on 26/06/2019.
 *
 */
public class DSDownloadRxJavaManager {

    public DSDownloadRxJavaManager() {
    }

    public ApiService getService(final MDownloadDataType mDownloadDataType, final IMProvider imProvider) {
        Retrofit retrofit = null;
        if (mDownloadDataType.getmDataType()== MDataType.JSON){
             retrofit  = RetrofitSingleton.getInstanceJson();
        }else if (mDownloadDataType.getmDataType()==MDataType.IMAGE){
            retrofit =RetrofitSingleton.getInstanceString();
        }

        ApiService service = retrofit.create(ApiService.class);
        service.getApiData()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<MDownloadDataType>() {
            @Override
            public void onSubscribe(Disposable d) {
                // called before request is started
                mDownloadDataType.getImDownloadDataType().onSubscribe(mDownloadDataType);
            }

            @Override
            public void onNext(MDownloadDataType mpDownloadDataType) {
                // called when response HTTP status is "200 OK"
                mDownloadDataType.setData(mpDownloadDataType.getData());
                mDownloadDataType.getImDownloadDataType().onNext(mDownloadDataType);

                // This call for provider to manage it
                imProvider.markAsDone(mDownloadDataType);

            }


            @Override
            public void onError(Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                mDownloadDataType.getImDownloadDataType().onError(mDownloadDataType, e);

                // This call for provider to manage it
                imProvider.onFailure(mDownloadDataType);

            }

            @Override
            public void onComplete() {
                // Updates UI with data
                // called when request is retried
                mDownloadDataType.getImDownloadDataType().onComplete();
                imProvider.markAsCancel(mDownloadDataType);

            }
        });

        return service;
    }

}


