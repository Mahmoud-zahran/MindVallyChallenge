package com.teztour.mindvallychallenge.mainActivityMVP;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.teztour.mindvallychallenge.R;
import com.teztour.mindvallychallenge.adapter.ImageListAdapter;
import com.teztour.mindvallychallenge.daggerNeededFiles.component.ApplicationComponent;
import com.teztour.mindvallychallenge.daggerNeededFiles.component.DaggerMainActivityComponent;
import com.teztour.mindvallychallenge.daggerNeededFiles.component.MainActivityComponent;
import com.teztour.mindvallychallenge.daggerNeededFiles.module.MainActivityContextModule;
import com.teztour.mindvallychallenge.daggerNeededFiles.module.MainActivityMvpModule;
import com.teztour.mindvallychallenge.daggerNeededFiles.qualifer.ActivityContext;
import com.teztour.mindvallychallenge.daggerNeededFiles.qualifer.ApplicationContext;
import com.teztour.mindvallychallenge.entity.Response;
import com.teztour.mindvallychallenge.root.MyApplication;
import com.teztour.mindvallychallenge.util.CustomProgressDialog;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    @Inject
    @ApplicationContext
    public Context context;

    @Inject
    @ActivityContext
    public Context activityContext;
    String TAG = "MainActivity";

    @Inject
    MainActivityPresenterImpl mainActivityPresenter;
    CustomProgressDialog mCustomProgressDialog;
  //  private DownloadDataTypeServiceProvider mProvider;
    private ArrayList<Response> users;

   // ImageView imageView;
 //   String url = "http://pastebin.com/raw/wgkJgazE";

   @BindView(R.id.DataShowActivity_recycler_view)
   RecyclerView voucherFlightsList_RecView;

    ImageListAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityComponent mainActivityComponent;


        ButterKnife.bind(this);
        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .mainActivityMvpModule(new MainActivityMvpModule(this))
                .applicationComponent(applicationComponent)
                .build();
        mainActivityComponent.injectMainActivity(this);
        mainActivityPresenter.loadFeedsData();
       // voucherFlightsList_RecView = (RecyclerView) findViewById(R.id.DataShowActivity_recycler_view);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(this);

        voucherFlightsList_RecView.setLayoutManager(LayoutManager);

       // users = new ArrayList<>();

       // mProvider = DownloadDataTypeServiceProvider.getInstance();
     //   Response mResponse;
//        MDownloadDataType mDataTypeImageCancel = new MDownloadDataTypeJson(url , new IDSDownloadDataType() {
//            @Override
//            public void onSubscribe(MDownloadDataType mDownloadDataType) {
//                Log.d("MainActivity", "onSubscribe: ");
//
//            }
//
//            @Override
//            public void onNext(MDownloadDataType mDownloadDataType) {
//                Log.d("MainActivity", "onNext: "+((MDownloadDataTypeJson)mDownloadDataType).getJsonText());
//                String response = new String(mDownloadDataType.getData(), StandardCharsets.UTF_8);
//                Response[] detailsResponses = new Gson().fromJson(response, Response[].class);
//
//                if (detailsResponses.length != 0)
//                {
//                    users.clear();
//                    Collections.addAll(users, detailsResponses);
//                }
//
//                Adapter = new ImageListAdapter(getApplicationContext(), users);
//
//                voucherFlightsList_RecView.setAdapter(Adapter);
//          //      imageView.setImageBitmap(((MDownloadDataTypeImage) mDownloadDataType).getImageBitmap());
//
//            }
//
//            @Override
//            public void onError(MDownloadDataType mDownloadDataType, Throwable e) {
//
//                Log.d("MainActivity", "onError: "+e.getMessage());
//
//           //         imageView.setImageResource(R.drawable.ic_launcher_background);
//
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d("MainActivity", "onComplete ");
//
//            }
//
//        });
//        mProvider.getRequest(mDataTypeImageCancel);

        }

    @Override
    public void showError(String call, String statusMessage) {
        if (call.equals("network error")) {
            Log.d(TAG, "showError:network error " + statusMessage);
            Toast.makeText(context, statusMessage, Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "showError: " + "error message");
            Toast.makeText(context, "error message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgress() {
        mCustomProgressDialog = new CustomProgressDialog(this);
        mCustomProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mCustomProgressDialog.show();
        Log.d(TAG, "showProgress: " + "showProgress message");
    }

    @Override
    public void hideProgress() {
        mCustomProgressDialog.dismiss();
        mCustomProgressDialog = null;
        Log.d(TAG, "hideProgress: " + "hideProgress message");
    }

    @Override
    public void showComplete() {
        users = mainActivityPresenter.getUsers();
        Log.d(TAG, "showComplete: " + "showComplete message");
    }

    @Override
    public void updateRecycleView(ArrayList<Response> users) {
        Adapter = new ImageListAdapter(getApplicationContext(), users);
                voucherFlightsList_RecView.setAdapter(Adapter);


    }
}
