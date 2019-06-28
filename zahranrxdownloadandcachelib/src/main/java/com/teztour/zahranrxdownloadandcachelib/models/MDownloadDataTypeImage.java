package com.teztour.zahranrxdownloadandcachelib.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.teztour.zahranrxdownloadandcachelib.interfaces.IMDownloadDataType;

/**
 * Created by Mahmoud Zahran on 26/06/2019.
 *
 */
public class MDownloadDataTypeImage extends MDownloadDataType {

    public MDownloadDataTypeImage(String url, IMDownloadDataType imDownloadDataType) {
        super(url, MDataType.IMAGE, imDownloadDataType);
    }

    @Override
    public MDownloadDataType getCopyOfMe(IMDownloadDataType imDownloadDataType) {
        MDownloadDataType mDownloadDataType = new MDownloadDataTypeImage(this.getUrl(), imDownloadDataType);
        return mDownloadDataType;
    }

    public Bitmap getImageBitmap(){
        Bitmap bitmap = BitmapFactory.decodeByteArray(getData(), 0, getData().length);
        return bitmap;
    }
}
