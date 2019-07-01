package com.teztour.mindvallychallenge.mainActivityMVP;

import android.util.Log;

import com.google.gson.Gson;
import com.teztour.mindvallychallenge.entity.Response;
import com.teztour.mindvallychallenge.util.ApiUrls;
import com.teztour.zahranrxdownloadandcachelib.callbackInterfaces.IDSDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataTypeJson;
import com.teztour.zahranrxdownloadandcachelib.utils.DownloadDataTypeServiceProvider;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;



public class MainActivityPresenterImpl implements MainActivityContract.Presenter  {

    private DownloadDataTypeServiceProvider feedsApi;
    private MainActivityContract.View mView;
    private ArrayList<Response> users;



    @Inject
    public MainActivityPresenterImpl( MainActivityContract.View mView){
       // this.feedsApi = feedsApi;//DownloadDataTypeServiceProvider.getInstance();
        this.mView = mView;


    }

    @Override
    public void loadFeedsData() {
        mView.showProgress();
        feedsApi=DownloadDataTypeServiceProvider.getInstance();
        users=new ArrayList<>();
        MDownloadDataType mDataTypeImageCancel = new MDownloadDataTypeJson(ApiUrls.BASE_URL, new IDSDownloadDataType() {
            @Override
            public void onSubscribe(MDownloadDataType mDownloadDataType) {
                Log.d("MainActivity", "onSubscribe: ");

            }

            @Override
            public void onNext(MDownloadDataType mDownloadDataType) {
                Log.d("MainActivity", "onNext: "+((MDownloadDataTypeJson)mDownloadDataType).getJsonText());
                String response = new String(mDownloadDataType.getData(), StandardCharsets.UTF_8);
                Response[] detailsResponses = new Gson().fromJson(response, Response[].class);

                if (detailsResponses.length != 0)
                {
                    users.clear();
                    Collections.addAll(users, detailsResponses);
                    mView.showComplete();
                    if (users!= null){
                        mView.updateRecycleView(users);
                    }

                }else{
                    mView.showError("message null","No Users found!" );
                }

                mView.hideProgress();


            }

            @Override
            public void onError(MDownloadDataType mDownloadDataType, Throwable e) {

               // Log.d("MainActivity", "onError: "+e.getMessage());
                mView.showError("network error","Error occurred");
                Log.e("MainActivity", e.getMessage(), e);
                mView.hideProgress();


            }

            @Override
            public void onComplete() {
                Log.d("MainActivity", "onComplete ");
                mView.hideProgress();
            }

        });
        feedsApi.getRequest(mDataTypeImageCancel);
    }

    @Override
    public ArrayList<Response> getUsers() {
        return users;
    }


}
