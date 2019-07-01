package com.teztour.mindvallychallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.teztour.mindvallychallenge.adapter.ImageListAdapter;
import com.teztour.mindvallychallenge.entity.Response;
import com.teztour.zahranrxdownloadandcachelib.callbackInterfaces.IDSDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataTypeJson;
import com.teztour.zahranrxdownloadandcachelib.utils.DownloadDataTypeServiceProvider;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private DownloadDataTypeServiceProvider mProvider;
    private ArrayList<Response> users;
    ImageView imageView;
    String url = "http://pastebin.com/raw/wgkJgazE";
    RecyclerView voucherFlightsList_RecView;
    ImageListAdapter Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        voucherFlightsList_RecView = (RecyclerView) findViewById(R.id.DataShowActivity_recycler_view);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(this);

        voucherFlightsList_RecView.setLayoutManager(LayoutManager);
        users = new ArrayList<>();

        mProvider = DownloadDataTypeServiceProvider.getInstance();
        Response mResponse;
        MDownloadDataType mDataTypeImageCancel = new MDownloadDataTypeJson(url , new IDSDownloadDataType() {
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
                }

                Adapter = new ImageListAdapter(getApplicationContext(), users);

                voucherFlightsList_RecView.setAdapter(Adapter);
          //      imageView.setImageBitmap(((MDownloadDataTypeImage) mDownloadDataType).getImageBitmap());

            }

            @Override
            public void onError(MDownloadDataType mDownloadDataType, Throwable e) {

                Log.d("MainActivity", "onError: "+e.getMessage());

           //         imageView.setImageResource(R.drawable.ic_launcher_background);

            }

            @Override
            public void onComplete() {
                Log.d("MainActivity", "onComplete ");

            }

        });
        mProvider.getRequest(mDataTypeImageCancel);

        }
}
