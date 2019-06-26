package com.teztour.zahranrxdownloadandcachelib.utils;

import android.util.Log;

import com.teztour.zahranrxdownloadandcachelib.ApiService;
import com.teztour.zahranrxdownloadandcachelib.interfaces.IMDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.interfaces.IMProvider;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Tamim Maaz on 9/18/2016.
 * This class to contact with MDownloadAsyncManager and MCachingManager
 * This class to manage the requests that go to MDownloadAsyncManager
 * If two or more request for the same url this class will manage them
 */
public class MProviderMDownloadDataType {
    private static MProviderMDownloadDataType instance;
    private HashMap<String, LinkedList<MDownloadDataType>> allRequestsByKey = new HashMap<>();
    private HashMap<String, ApiService> allRequestsClient = new HashMap<>();
    private MCachingManager mCachingManager;

    private MProviderMDownloadDataType() {
        mCachingManager = MCachingManager.getInstance();
    }

    public static MProviderMDownloadDataType getInstance() {
        if (instance == null) {
            instance = new MProviderMDownloadDataType();
        }
        return instance;
    }

    public void getRequest(final MDownloadDataType mDownloadDataType) {
        final String mKey = mDownloadDataType.getKeyMD5();
        // Check if exist in the cache
        MDownloadDataType mDownloadDataTypeFromCache = mCachingManager.getMDownloadDataType(mKey);
        if (mDownloadDataTypeFromCache != null) {
            mDownloadDataTypeFromCache.comeFrom = "Cache";
            // call interface
            IMDownloadDataType imDownloadDataType = mDownloadDataType.getImDownloadDataType();
            imDownloadDataType.onSubscribe(mDownloadDataTypeFromCache);
            imDownloadDataType.onNext(mDownloadDataTypeFromCache);
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
        MDownloadDataType newMDownloadDataType = mDownloadDataType.getCopyOfMe(new IMDownloadDataType() {
            @Override
            public void onSubscribe(MDownloadDataType mDownloadDataType) {
                for (MDownloadDataType m : allRequestsByKey.get(mDownloadDataType.getKeyMD5())) {
                    m.setData(mDownloadDataType.getData());
                    m.getImDownloadDataType().onSubscribe(m);
                }
            }

            @Override
            public void onNext(MDownloadDataType mDownloadDataType) {
                for (MDownloadDataType m : allRequestsByKey.get(mDownloadDataType.getKeyMD5())) {
                    m.setData(mDownloadDataType.getData());
                    Log.e("HERRRR", m.comeFrom);
                    m.getImDownloadDataType().onNext(m);
                }
                allRequestsByKey.remove(mDownloadDataType.getKeyMD5());
            }

            @Override
            public void onError(MDownloadDataType mDownloadDataType, Throwable e) {
                for (MDownloadDataType m : allRequestsByKey.get(mDownloadDataType.getKeyMD5())) {
                    m.setData(mDownloadDataType.getData());
                    m.getImDownloadDataType().onError(m, e);
                }
                allRequestsByKey.remove(mDownloadDataType.getKeyMD5());
            }

            @Override
            public void onComplete() {
                for (MDownloadDataType m : allRequestsByKey.get(mDownloadDataType.getKeyMD5())) {
                    m.setData(mDownloadDataType.getData());
                    m.getImDownloadDataType().onComplete();
            }
            }
        });

        // Get from download manager
        final MDownloadAsyncManager mDownloadAsyncManager = new MDownloadAsyncManager();
       ApiService client= mDownloadAsyncManager.getService(newMDownloadDataType, new IMProvider() {
           @Override
           public void markAsDone(MDownloadDataType mDownloadDataType) {
               // put in the cache when mark as done
               mCachingManager.putMDownloadDataType(mDownloadDataType.getKeyMD5(), mDownloadDataType);
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
            mDownloadDataType.getImDownloadDataType().onError(mDownloadDataType, new Exception("canceled"));
        }
    }

    public boolean isRequestDone(){
        return allRequestsByKey.size() == 0;
    }

    public void clearTheCash(){
        mCachingManager.clearTheCash();
    }
}
