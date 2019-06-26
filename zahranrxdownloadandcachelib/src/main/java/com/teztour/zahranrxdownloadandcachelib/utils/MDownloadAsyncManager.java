package com.teztour.zahranrxdownloadandcachelib.utils;


import com.teztour.zahranrxdownloadandcachelib.ApiService;
import com.teztour.zahranrxdownloadandcachelib.interfaces.IMProvider;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.module.RetrofitSingleton;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Tamim Maaz on 9/18/2016.
 */
public class MDownloadAsyncManager {

    public MDownloadAsyncManager() {
    }

    public ApiService getService(final MDownloadDataType mDownloadDataType, final IMProvider imProvider) {
        Retrofit retrofit = RetrofitSingleton.getInstanceJson();
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
                imProvider.markAsCancel(mDownloadDataType);

            }
        });

        return service;
    }


//    public AsyncHttpClient get(final MDownloadDataType mDownloadDataType, final IMProvider imProvider) {
//        AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);
//
//
//        client.get(mDownloadDataType.getUrl(), new AsyncHttpResponseHandler() {
//            @Override
//            public void onStart() {
//                // called before request is started
//                mDownloadDataType.getImDownloadDataType().onStart(mDownloadDataType);
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
//                // called when response HTTP status is "200 OK"
//                mDownloadDataType.setData(response);
//                mDownloadDataType.getImDownloadDataType().onSuccess(mDownloadDataType);
//
//                // This call for provider to manage it
//                imProvider.markAsDone(mDownloadDataType);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
//                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//                mDownloadDataType.getImDownloadDataType().onFailure(mDownloadDataType, statusCode, errorResponse, e);
//
//                // This call for provider to manage it
//                imProvider.onFailure(mDownloadDataType);
//            }
//
//            @Override
//            public void onRetry(int retryNo) {
//                // called when request is retried
//                mDownloadDataType.getImDownloadDataType().onRetry(mDownloadDataType, retryNo);
//            }
//
//            @Override
//            public void onCancel() {
//                super.onCancel();
//
//                // called when request is retried
//                imProvider.markAsCancel(mDownloadDataType);
//            }
//        });
//
//        return client;
//    }
}


