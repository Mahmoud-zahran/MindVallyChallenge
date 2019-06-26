package com.teztour.zahranrxdownloadandcachelib.interfaces;

import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;

/**
 * Created by Tamim Maaz on 9/18/2016.
 */
public interface IMDownloadDataType {
    public void onSubscribe(MDownloadDataType mDownloadDataType);

    public void onNext(MDownloadDataType mDownloadDataType);

    public void onError(MDownloadDataType mDownloadDataType,Throwable e);

    public void onComplete();
}
