package com.teztour.zahranrxdownloadandcachelib.callbackInterfaces;

import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;

/**
 * Created by Mahmoud Zahran on 26/06/2019.
 *
 */
public interface IDSProvider {
    public void markAsDone(MDownloadDataType mDownloadDataType);
    public void onFailure(MDownloadDataType mDownloadDataType);
    public void markAsCancel(MDownloadDataType mDownloadDataType);
}
