package com.teztour.mindvallychallenge.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

import com.teztour.mindvallychallenge.R;
import com.victor.loading.rotate.RotateLoading;


/**
 * Created by Mahmoud zahran on 3/2/2017.
 */

public class CustomProgressDialog extends Dialog {
    public Context c;
    public Dialog d;

    public CustomProgressDialog(Context a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
//        NewtonCradleLoading newtonCradleLoading=findViewById(R.id.newton_cradle_loading);
//        newtonCradleLoading.start();
//        BookLoading bookLoading=findViewById(R.id.bookloading);
//        bookLoading.start();
        RotateLoading rotateLoading=findViewById(R.id.rotateloading);
        rotateLoading.setLoadingColor(Color.WHITE);
        rotateLoading.start();

    }

    public static ProgressDialog create(Context context, String msg, int theme){

        ProgressDialog progressDialog = new ProgressDialog(context, theme);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        //progressBar.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return progressDialog;
    }

    public static RotateLoading create(Context context){
        RotateLoading rotateLoading=new RotateLoading(context);
        rotateLoading.setLoadingColor(Color.WHITE);
        rotateLoading.start();

           //     ProgressDialog progressDialog = new ProgressDialog(context, R.style.CustomDialog);
      //  progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
       // progressDialog.setMessage(context.getString(R.string.pleaseWait));
     //   progressDialog.setCancelable(false);
        //progressBar.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
      //  return progressDialog;
        return rotateLoading;
    }
}
