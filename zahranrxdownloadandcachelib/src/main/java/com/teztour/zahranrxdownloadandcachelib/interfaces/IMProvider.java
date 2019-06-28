package com.teztour.zahranrxdownloadandcachelib.interfaces;

import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;

/**
 * Created by Mahmoud Zahran on 26/06/2019.
 *
 */
public interface IMProvider {
    public void markAsDone(MDownloadDataType mDownloadDataType);
    public void onFailure(MDownloadDataType mDownloadDataType);
    public void markAsCancel(MDownloadDataType mDownloadDataType);
}
