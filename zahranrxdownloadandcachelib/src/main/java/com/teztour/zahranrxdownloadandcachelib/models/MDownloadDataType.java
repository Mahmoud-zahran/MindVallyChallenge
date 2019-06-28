package com.teztour.zahranrxdownloadandcachelib.models;

import com.teztour.zahranrxdownloadandcachelib.interfaces.IMDownloadDataType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Mahmoud Zahran on 26/06/2019.
 *
 */
public abstract class MDownloadDataType {
    private String url;
    private byte[] data;
    private MDataType mDataType;
    private IMDownloadDataType imDownloadDataType;

    private String keyMD5;

    // User For Just For Test
    public String comeFrom = "Net";

    protected MDownloadDataType(String url, MDataType mDataType, IMDownloadDataType imDownloadDataType){
        this.url = url;
        this.mDataType = mDataType;
        this.imDownloadDataType = imDownloadDataType;
        this.keyMD5 = md5(this.url);
    }

    public String getKeyMD5() {
        return keyMD5;
    }
    
    public String getUrl() {
        return url;
    }

    public byte[] getData() {
        return data;
    }

    public MDataType getmDataType() {
        return mDataType;
    }

    public IMDownloadDataType getImDownloadDataType() {
        return imDownloadDataType;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public abstract MDownloadDataType getCopyOfMe(IMDownloadDataType imDownloadDataType);


    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
