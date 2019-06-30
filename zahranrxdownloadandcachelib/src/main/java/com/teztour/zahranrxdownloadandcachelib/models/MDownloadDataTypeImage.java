package com.teztour.zahranrxdownloadandcachelib.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.teztour.zahranrxdownloadandcachelib.callbackInterfaces.IDSDownloadDataType;

/**
 * Created by Mahmoud Zahran on 26/06/2019.
 *
 */
public class MDownloadDataTypeImage extends MDownloadDataType {

    public MDownloadDataTypeImage(String url, IDSDownloadDataType imIDSDownloadDataType) {
        super(url, MDataType.IMAGE, imIDSDownloadDataType);
    }

    @Override
    public MDownloadDataType getCopyOfMe(IDSDownloadDataType imIDSDownloadDataType) {
        MDownloadDataType mDownloadDataType = new MDownloadDataTypeImage(this.getUrl(), imIDSDownloadDataType);
        return mDownloadDataType;
    }

    public Bitmap getImageBitmap(){
        Bitmap bitmap = BitmapFactory.decodeByteArray(getData(), 0, getData().length);
        return bitmap;
    }
}
