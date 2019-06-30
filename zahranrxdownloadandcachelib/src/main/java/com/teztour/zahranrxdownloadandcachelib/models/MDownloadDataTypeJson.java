package com.teztour.zahranrxdownloadandcachelib.models;

import com.google.gson.Gson;
import com.teztour.zahranrxdownloadandcachelib.callbackInterfaces.IDSDownloadDataType;

import java.lang.reflect.Type;

/**
 * Created by Mahmoud Zahran on 26/06/2019.
 *
 */
public class MDownloadDataTypeJson extends MDownloadDataType {

    public MDownloadDataTypeJson(String url, IDSDownloadDataType imIDSDownloadDataType) {
        super(url, MDataType.JSON, imIDSDownloadDataType);
    }

    @Override
    public MDownloadDataType getCopyOfMe(IDSDownloadDataType imIDSDownloadDataType) {
        MDownloadDataType mDownloadDataType = new MDownloadDataTypeJson(this.getUrl(), imIDSDownloadDataType);
        return mDownloadDataType;
    }

    public String getJsonText() {
        try {
            String str = new String(getData(), "UTF-8");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public Object getJson(Type type) {
        Gson gson = new Gson();
        return gson.fromJson(getJsonText(), type);
    }
}
