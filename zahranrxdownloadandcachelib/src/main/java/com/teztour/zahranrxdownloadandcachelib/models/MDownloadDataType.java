package com.teztour.zahranrxdownloadandcachelib.models;

import com.teztour.zahranrxdownloadandcachelib.ApiUrls;
import com.teztour.zahranrxdownloadandcachelib.callbackInterfaces.IDSDownloadDataType;

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
    private IDSDownloadDataType imIDSDownloadDataType;

    private String keyMD5;

    // User For Just For Test
    public String comeFrom = "Net";

    protected MDownloadDataType(String url, MDataType mDataType, IDSDownloadDataType imIDSDownloadDataType){
        this.url = url;
        ApiUrls.BASE_URL=url;
        this.mDataType = mDataType;
        this.imIDSDownloadDataType = imIDSDownloadDataType;
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

    public IDSDownloadDataType getImIDSDownloadDataType() {
        return imIDSDownloadDataType;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public abstract MDownloadDataType getCopyOfMe(IDSDownloadDataType imIDSDownloadDataType);


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
