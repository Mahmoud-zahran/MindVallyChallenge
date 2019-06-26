package com.teztour.zahranrxdownloadandcachelib.interfaces;

import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;

/**
 * Created by Tamim Maaz on 9/18/2016.
 */
public interface IMProvider {
    public void markAsDone(MDownloadDataType mDownloadDataType);
    public void onFailure(MDownloadDataType mDownloadDataType);
    public void markAsCancel(MDownloadDataType mDownloadDataType);
}
