package com.gethelp.huyngh.helpmedemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        initDialogLoading();
    }

    private void initDialogLoading() {
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle(R.string.loading);
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
