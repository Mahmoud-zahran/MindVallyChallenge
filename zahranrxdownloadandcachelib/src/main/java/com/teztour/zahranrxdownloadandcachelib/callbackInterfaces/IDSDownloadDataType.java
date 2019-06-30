package com.teztour.zahranrxdownloadandcachelib.callbackInterfaces;

import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;

/**
 * Created by Mahmoud Zahran on 26/06/2019.
 *
 */
public interface IDSDownloadDataType {

    public void onSubscribe(MDownloadDataType mDownloadDataType);

    public void onNext(MDownloadDataType mDownloadDataType);

    public void onError(MDownloadDataType mDownloadDataType,Throwable e);

    public void onComplete();
}
