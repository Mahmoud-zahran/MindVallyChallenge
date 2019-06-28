package com.teztour.zahranrxdownloadandcachelib.interfaces;

import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;

/**
 * Created by Mahmoud Zahran on 26/06/2019.
 *
 */
public interface IMDownloadDataType {
    public void onSubscribe(MDownloadDataType mDownloadDataType);

    public void onNext(MDownloadDataType mDownloadDataType);

    public void onError(MDownloadDataType mDownloadDataType,Throwable e);

    public void onComplete();
}
