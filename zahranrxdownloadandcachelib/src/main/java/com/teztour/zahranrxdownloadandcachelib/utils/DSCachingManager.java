package com.teztour.zahranrxdownloadandcachelib.utils;

import android.util.LruCache;

import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;

/**
 * Created by Mahmoud Zahran on 25/06/2019.
 *
 * this class as data service as a caching manager.
 * have the following features
 * - private constructor to make singleton design pattern
 * - provide instance only from get instance function.
 * - get downloaded Data using the key that used to save it before.
 * - save downloaded Data to memory using LruCache class key,value.
 * - finally we have Clear cache function to empty cache.
 *
 */
public class DSCachingManager {
    private int maxCacheSize;
    private static DSCachingManager instance;
    private LruCache<String, MDownloadDataType> mDownloadDataTypeCache;

    private DSCachingManager() {
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        maxCacheSize =   maxMemory / 8; // 4 * 1024 * 1024; // 4MiB
        mDownloadDataTypeCache = new LruCache<>(maxCacheSize);
    }
   //provide instance
    public static DSCachingManager getInstance() {
        if (instance == null) {
            instance = new DSCachingManager();
        }
        return instance;
    }

    public MDownloadDataType getMDownloadDataType(String key) {
        return mDownloadDataTypeCache.get(key);
    }

    public boolean saveMDownloadDataType(String key, MDownloadDataType mDownloadDataType) {
        return mDownloadDataTypeCache.put(key, mDownloadDataType) != null;
    }

    public void clearTheCache() {
        mDownloadDataTypeCache.evictAll();
    }
}
