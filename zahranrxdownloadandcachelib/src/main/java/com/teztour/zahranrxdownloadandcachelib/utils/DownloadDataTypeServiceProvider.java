package com.teztour.zahranrxdownloadandcachelib.utils;

import android.util.Log;

import com.teztour.zahranrxdownloadandcachelib.ApiService;
import com.teztour.zahranrxdownloadandcachelib.callbackInterfaces.IDSDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.callbackInterfaces.IDSProvider;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Mahmoud Zahran on 26/06/2019.
 *
 * This class to contact with DSDownloadRxJavaManager and DSCachingManager
 * This class to manage the requests that go to DSDownloadRxJavaManager
 * If two or more request for the same url this class will manage them
 */
public class DownloadDataTypeServiceProvider {
    private static DownloadDataTypeServiceProvider instance;
    private HashMap<String, LinkedList<MDownloadDataType>> allRequestsByKey = new HashMap<>();
    private HashMap<String, ApiService> allRequestsClient = new HashMap<>();
    private DSCachingManager mDSCachingManager;

    private DownloadDataTypeServiceProvider() {
        mDSCachingManager = DSCachingManager.getInstance();
    }

    public static DownloadDataTypeServiceProvider getInstance() {
        if (instance == null) {
            instance = new DownloadDataTypeServiceProvider();
        }
        return instance;
    }

    public void getRequest(final MDownloadDataType mDownloadDataType) {
        final String mKey = mDownloadDataType.getKeyMD5();
        // Check if exist in the cache
        MDownloadDataType mDownloadDataTypeFromCache = mDSCachingManager.getMDownloadDataType(mKey);
        if (mDownloadDataTypeFromCache != null) {
            mDownloadDataTypeFromCache.comeFrom = "Cache";
            // call interface
            IDSDownloadDataType imIDSDownloadDataType = mDownloadDataType.getImIDSDownloadDataType();
            imIDSDownloadDataType.onSubscribe(mDownloadDataTypeFromCache);
            imIDSDownloadDataType.onNext(mDownloadDataTypeFromCache);
            return;
        }

        // This will run if two request come for same url
        if(allRequestsByKey.containsKey(mKey)){
            mDownloadDataType.comeFrom = "Cache";
            allRequestsByKey.get(mKey).add(mDownloadDataType);
            return;
        }

        // Put it in the allRequestsByKey to manage it after done or cancel
        if (allRequestsByKey.containsKey(mKey)) {
            allRequestsByKey.get(mKey).add(mDownloadDataType);
        } else {
            LinkedList<MDownloadDataType> lstMDDataType = new LinkedList<>();
            lstMDDataType.add(mDownloadDataType);
            allRequestsByKey.put(mKey, lstMDDataType);
        }

        // Create new MDownloadDataType by clone it from the parameter
        MDownloadDataType newMDownloadDataType = mDownloadDataType.getCopyOfMe(new IDSDownloadDataType() {
            @Override
            public void onSubscribe(MDownloadDataType mDownloadDataType) {
                for (MDownloadDataType m : allRequestsByKey.get(mDownloadDataType.getKeyMD5())) {
                    m.setData(mDownloadDataType.getData());
                    m.getImIDSDownloadDataType().onSubscribe(m);
                }
            }

            @Override
            public void onNext(MDownloadDataType mDownloadDataType) {
                for (MDownloadDataType m : allRequestsByKey.get(mDownloadDataType.getKeyMD5())) {
                    m.setData(mDownloadDataType.getData());
                    Log.e("HERRRR", m.comeFrom);
                    m.getImIDSDownloadDataType().onNext(m);
                }
                allRequestsByKey.remove(mDownloadDataType.getKeyMD5());
            }

            @Override
            public void onError(MDownloadDataType mDownloadDataType, Throwable e) {
                for (MDownloadDataType m : allRequestsByKey.get(mDownloadDataType.getKeyMD5())) {
                    m.setData(mDownloadDataType.getData());
                    m.getImIDSDownloadDataType().onError(m, e);
                }
                allRequestsByKey.remove(mDownloadDataType.getKeyMD5());
            }

            @Override
            public void onComplete() {
                if (!allRequestsByKey.isEmpty())
                    if (!allRequestsByKey.get(mDownloadDataType.getKeyMD5()).isEmpty())
                        for (MDownloadDataType m : allRequestsByKey.get(mDownloadDataType.getKeyMD5())) {
                            m.setData(mDownloadDataType.getData());
                            m.getImIDSDownloadDataType().onComplete();
            }
            }
        });

        // Get from download manager
        final DSDownloadRxJavaManager mDSDownloadRxJavaManager = new DSDownloadRxJavaManager();
        ApiService client= mDSDownloadRxJavaManager.getService(newMDownloadDataType, new IDSProvider() {
           @Override
           public void markAsDone(MDownloadDataType mDownloadDataType) {
               // put in the cache when mark as done
               mDSCachingManager.saveMDownloadDataType(mDownloadDataType.getKeyMD5(), mDownloadDataType);
               Log.e("HERRRR", "MARK AS DONE");
               allRequestsClient.remove(mDownloadDataType.getKeyMD5());
           }

           @Override
           public void onFailure(MDownloadDataType mDownloadDataType) {
               allRequestsClient.remove(mDownloadDataType.getKeyMD5());
           }

           @Override
           public void markAsCancel(MDownloadDataType mDownloadDataType) {
               allRequestsClient.remove(mDownloadDataType.getUrl());
           }
       });
        allRequestsClient.put(mKey, client);

    }

    public void cancelRequest(MDownloadDataType mDownloadDataType){
        if(allRequestsByKey.containsKey(mDownloadDataType.getKeyMD5())) {
            allRequestsByKey.get(mDownloadDataType.getKeyMD5()).remove(mDownloadDataType);
            mDownloadDataType.comeFrom = "Canceled";
            mDownloadDataType.getImIDSDownloadDataType().onError(mDownloadDataType, new Exception("canceled"));
        }
    }

    public boolean isRequestDone(){
        return allRequestsByKey.size() == 0;
    }

    public void clearTheCash(){
        mDSCachingManager.clearTheCache();
    }
}
