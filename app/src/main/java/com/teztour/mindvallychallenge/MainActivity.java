package com.teztour.mindvallychallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.teztour.zahranrxdownloadandcachelib.callbackInterfaces.IDSDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataTypeImage;
import com.teztour.zahranrxdownloadandcachelib.utils.DownloadDataTypeServiceProvider;

public class MainActivity extends AppCompatActivity {
    private DownloadDataTypeServiceProvider mProvider;
    ImageView imageView;
    String url = "https://images.unsplash.com/photo-1464519586905-a8c004d307cc/";/*"http://cronws.tez-tour.com/rest/getAppVersions/iguideEG/";*"http://pastebin.com/raw/wgkJgazE/";*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.full_image_view);
        mProvider = DownloadDataTypeServiceProvider.getInstance();
        MDownloadDataType mDataTypeImageCancel = new MDownloadDataTypeImage(url , new IDSDownloadDataType() {
            @Override
            public void onSubscribe(MDownloadDataType mDownloadDataType) {
                Log.d("MainActivity", "onSubscribe: ");

            }

            @Override
            public void onNext(MDownloadDataType mDownloadDataType) {
                Log.d("MainActivity", "onNext: "+mDownloadDataType.getData());
                imageView.setImageBitmap(((MDownloadDataTypeImage) mDownloadDataType).getImageBitmap());

            }

            @Override
            public void onError(MDownloadDataType mDownloadDataType, Throwable e) {

                Log.d("MainActivity", "onError: "+e.getMessage());

                    imageView.setImageResource(R.drawable.ic_launcher_background);

            }

            @Override
            public void onComplete() {
                Log.d("MainActivity", "onComplete ");

            }

        });
        mProvider.getRequest(mDataTypeImageCancel);

        }
}
