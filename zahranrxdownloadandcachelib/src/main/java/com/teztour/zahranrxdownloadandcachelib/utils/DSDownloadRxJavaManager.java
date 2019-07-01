package com.teztour.zahranrxdownloadandcachelib.utils;


import com.teztour.zahranrxdownloadandcachelib.ApiService;
import com.teztour.zahranrxdownloadandcachelib.callbackInterfaces.IDSProvider;
import com.teztour.zahranrxdownloadandcachelib.models.MDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.module.RetrofitSingleton;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Created by Mahmoud Zahran on 26/06/2019.
 *
 */
public class DSDownloadRxJavaManager {

    public DSDownloadRxJavaManager() {
    }

    public ApiService getService(final MDownloadDataType mDownloadDataType, final IDSProvider imIDSProvider) {
        Retrofit retrofit = RetrofitSingleton.getInstanceJson();
        if (mDownloadDataType.getmDataType()== MDataType.JSON){
            retrofit =RetrofitSingleton.getInstanceString();

          //    retrofit  = RetrofitSingleton.getInstanceJson();
        }else if (mDownloadDataType.getmDataType()==MDataType.IMAGE){
            retrofit =RetrofitSingleton.getInstanceString();
        }

        ApiService service = retrofit.create(ApiService.class);
        service.getApiData()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
                // called before request is started
                mDownloadDataType.getImIDSDownloadDataType().onSubscribe(mDownloadDataType);
            }

            @Override
            public void onNext(ResponseBody mData) {
                // called when response HTTP status is "200 OK"
               // byte[] mbData=mData.getBytes();
              //  try {

                try {
                    mDownloadDataType.setData(mData.source().readByteArray());/*.getBytes("US-ASCII")*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
                mDownloadDataType.getImIDSDownloadDataType().onNext(mDownloadDataType);

                // This call for provider to manage it
                imIDSProvider.markAsDone(mDownloadDataType);

            }


            @Override
            public void onError(Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                mDownloadDataType.getImIDSDownloadDataType().onError(mDownloadDataType, e);

                // This call for provider to manage it
                imIDSProvider.onFailure(mDownloadDataType);

            }

            @Override
            public void onComplete() {
                // Updates UI with data
                // called when request is retried
                mDownloadDataType.getImIDSDownloadDataType().onComplete();
                imIDSProvider.markAsCancel(mDownloadDataType);

            }
        });

        return service;
    }

}


