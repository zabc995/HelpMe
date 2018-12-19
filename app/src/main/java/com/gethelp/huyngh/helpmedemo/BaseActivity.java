package com.gethelp.huyngh.helpmedemo;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.view.Window;

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        initDialogLoading();
    }

    private void initDialogLoading() {
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle(R.string.loading);
        progressDialog.setMessage("Please waiting ...");
        progressDialog.setCanceledOnTouchOutside(true);
    }

    protected void showDialogLoading(){
        if(progressDialog!=null){
            progressDialog.show();
        }
    }
    protected void dismissDialog(){
        if(progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
