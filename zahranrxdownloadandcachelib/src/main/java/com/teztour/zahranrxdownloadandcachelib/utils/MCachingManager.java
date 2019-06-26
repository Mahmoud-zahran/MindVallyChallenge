package com.teztour.zahranrxdownloadandcachelib.utils;

import android.util.LruCache;

import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;

/**
 * Created by Tamim Maaz on 9/18/2016.
 */
public class MCachingManager {
    private int maxCacheSize;
    private static MCachingManager instance;
    private LruCache<String, MDownloadDataType> mDownloadDataTypeCache;

    private MCachingManager() {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        maxCacheSize =   maxMemory / 8; // 4 * 1024 * 1024; // 4MiB
        mDownloadDataTypeCache = new LruCache<>(maxCacheSize);
    }

    public static MCachingManager getInstance() {
        if (instance == null) {
            instance = new MCachingManager();
        }
        return instance;
    }

    public MDownloadDataType getMDownloadDataType(String key) {
        return mDownloadDataTypeCache.get(key);
    }

    public boolean putMDownloadDataType(String key, MDownloadDataType mDownloadDataType) {
        return mDownloadDataTypeCache.put(key, mDownloadDataType) != null;
    }

    public void clearTheCash() {
        mDownloadDataTypeCache.evictAll();
    }
}
