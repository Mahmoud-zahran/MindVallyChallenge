package com.teztour.mindvallychallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.teztour.zahranrxdownloadandcachelib.interfaces.IDSDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataTypeJson;
import com.teztour.zahranrxdownloadandcachelib.utils.DownloadDataTypeServiceProvider;

public class MainActivity extends AppCompatActivity {
    private DownloadDataTypeServiceProvider mProvider;
    ImageView imageView;
    String url = "http://cronws.tez-tour.com/rest/getAppVersions/iguideEG/";//"http://pastebin.com/raw/wgkJgazE/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProvider = DownloadDataTypeServiceProvider.getInstance();
        MDownloadDataType mDataTypeImageCancel = new MDownloadDataTypeJson(url , new IDSDownloadDataType() {
            @Override
            public void onSubscribe(MDownloadDataType mDownloadDataType) {

            }

            @Override
            public void onNext(MDownloadDataType mDownloadDataType) {
                Log.d("MainActivity", "onNext: "+mDownloadDataType.getData());
            //    imageView.setImageBitmap(((MDownloadDataTypeImage) mDownloadDataType).getImageBitmap());

            }

            @Override
            public void onError(MDownloadDataType mDownloadDataType, Throwable e) {
            //    imageView.setImageResource(R.drawable.ic_menu_gallery);

            }

            @Override
            public void onComplete() {

            }

        });
        mProvider.getRequest(mDataTypeImageCancel);

        }
}
