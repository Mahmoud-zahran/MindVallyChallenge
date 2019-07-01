package com.teztour.mindvallychallenge.testCases;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.teztour.mindvallychallenge.R;
import com.teztour.zahranrxdownloadandcachelib.callbackInterfaces.IDSDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataType;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataTypeImage;
import com.teztour.zahranrxdownloadandcachelib.models.MDownloadDataTypeJson;
import com.teztour.zahranrxdownloadandcachelib.utils.DownloadDataTypeServiceProvider;

import java.lang.reflect.Type;

public class TestingActivity extends AppCompatActivity {
    private EditText txtResult;
    private EditText txtSummery;
    private DownloadDataTypeServiceProvider mProvider;
    private int lineIndex = 0;
    private int counterForHundler = 0;
    private int showSpeed = 20;
    private int countImageComeFromNet = 0;
    private int countImageComeFromCashe = 0;
    private int countJsonComeFromNet = 0;
    private int countJsonComeFromCashe = 0;
    private int countCanceled = 0;
    private int countFailure = 0;

    private Handler mHandler = new Handler();
    private String[] urlsImageArray =
            {
                    "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32&s=63f1d805cffccb834cf839c719d91702",
                    "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64&s=ef631d113179b3137f911a05fea56d23",
                    "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128&s=622a88097cf6661f84cd8942d851d9a2",
                    "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64&s=ef631d113179b3137f911a05fea56d23",
                    "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64&s=ef631d113179b3137f911a05fea56d23",
                    "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32&s=63f1d805cffccb834cf839c719d91702"
            };
    private String[] urlsJsonArray =
            {
                    "http://ip.jsontest.com/",
                    "http://pastebin.com/raw/wgkJgazE",
                    "http://pastebin.com/raw/wgkJgazE"
            };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        txtResult = (EditText) findViewById(R.id.txtResult);
        txtSummery = (EditText) findViewById(R.id.txtSummery);

        mProvider = DownloadDataTypeServiceProvider.getInstance();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
            txtResult.setText("");
            lineIndex = 0;
            counterForHundler = 0;
            countImageComeFromCashe = 0;
            countImageComeFromNet = 0;
            countJsonComeFromNet = 0;
            countJsonComeFromCashe = 0;
            countCanceled = 0;
            countFailure = 0;
            runTestCode();
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    runTestCode();
//                }
//            }, 5000);

            return true;
        } else if (id == R.id.action_clearCashe) {
            mProvider.clearTheCash();
            Toast.makeText(this, "Cache Cleared", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void runTestCode() {
        int i = 0;
        // Get Images
        for (String str : urlsImageArray) {
            MDownloadDataType mDataTypeImage = new MDownloadDataTypeImage(str, new InterfaceForDataType("Image-" + i++));
            mProvider.getRequest(mDataTypeImage);
        }
        MDownloadDataType mDataTypeImageCancel = new MDownloadDataTypeImage("https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64&s=ef631d113179b3137f911a05fea56d23", new InterfaceForDataType("Image-Cancel"));
        mProvider.getRequest(mDataTypeImageCancel);
        mProvider.cancelRequest(mDataTypeImageCancel);
        i = 0;
        // Get JSON
        try {
            for (String str : urlsJsonArray) {
                MDownloadDataType mDataTypeJson = new MDownloadDataTypeJson(str, new InterfaceForDataType("Json-" + i++));
                mProvider.getRequest(mDataTypeJson);
            }
        }catch (Exception e){
            Log.e("Test", "runTestCode: ", e);
        }

    }

    private void logInEditText(final String msg) {
        counterForHundler++;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (lineIndex == 0)
                    txtResult.append(lineIndex++ + ": " + msg);
                else
                    txtResult.append("\n" + lineIndex++ + ": " + msg);
                txtSummery.setText("Image From Net: " + countImageComeFromNet);
                txtSummery.append("\nImage From Cashe: " + countImageComeFromCashe);
                txtSummery.append("\nJson From Net: " + countJsonComeFromNet);
                txtSummery.append("\nJson From Cache: " + countJsonComeFromCashe);
                txtSummery.append("\nCanceled: " + countCanceled);
                txtSummery.append("\nFailure: " + countFailure);
            }
        }, counterForHundler * showSpeed);

    }

    public class InterfaceForDataType implements IDSDownloadDataType {
        private String name;

        public InterfaceForDataType(String name) {
            this.name = name;
        }



        @Override
        public void onSubscribe(MDownloadDataType mDownloadDataType) {
            logInEditText("********************");
            logInEditText("********************");
            logInEditText("OnStart Call");
            logInEditText("Request: " + name);
            logInEditText("Come From: " + mDownloadDataType.comeFrom);
            logInEditText("Data Type: " + mDownloadDataType.getmDataType().toString());
            logInEditText("Key MD5: " + mDownloadDataType.getKeyMD5());
            logInEditText("********************");
            logInEditText("********************");
            logInEditText("\n");
        }

        @Override
        public void onNext(MDownloadDataType mDownloadDataType) {
            if (mDownloadDataType.getmDataType() == MDataType.IMAGE)
                if (mDownloadDataType.comeFrom == "Net")
                    countImageComeFromNet++;
                else
                    countImageComeFromCashe++;
            else if (mDownloadDataType.getmDataType() == MDataType.JSON)
                if (mDownloadDataType.comeFrom == "Net")
                    countJsonComeFromNet++;
                else
                    countJsonComeFromCashe++;
            logInEditText("********************");
            logInEditText("********************");
            logInEditText("onSuccess Call");
            logInEditText("Request: " + name);
            logInEditText("Come From: " + mDownloadDataType.comeFrom);
            logInEditText("Data Type: " + mDownloadDataType.getmDataType().toString());
            logInEditText("Data Length: " + mDownloadDataType.getData().length);
            logInEditText("Key MD5: " + mDownloadDataType.getKeyMD5());


            if (mDownloadDataType.getmDataType() == MDataType.JSON) {
                Type type = new TypeToken<IPClass>() {
                }.getType();
                IPClass pp = (IPClass) ((MDownloadDataTypeJson) mDownloadDataType).getJson(type);
                logInEditText("IP: " + pp.getIp());
            }

            logInEditText("********************");
            logInEditText("********************");
            logInEditText("\n");
        }

        @Override
        public void onError(MDownloadDataType mDownloadDataType, Throwable e) {
            if (mDownloadDataType.comeFrom == "Canceled")
                countCanceled++;
            else
                countFailure++;
            logInEditText("********************");
            logInEditText("********************");
            logInEditText("onFailure Call");
            logInEditText("Request: " + name);
            logInEditText("Come From: " + mDownloadDataType.comeFrom);
            logInEditText("Data Type: " + mDownloadDataType.getmDataType().toString());
            logInEditText("Key MD5: " + mDownloadDataType.getKeyMD5());
            logInEditText("********************");
            logInEditText("********************");
            logInEditText("\n");
        }

        @Override
        public void onComplete() {
            logInEditText("********************");
            logInEditText("********************");
            logInEditText("onComplete Call");
            logInEditText("Request: " + name);
            logInEditText("********************");
            logInEditText("********************");
            logInEditText("\n");
        }
    }

    public class IPClass {
        @SerializedName("ip")
        @Expose
        private String ip;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }

}
