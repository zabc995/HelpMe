package com.gethelp.huyngh.helpmedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class StartActivity extends Activity {

    private ImageView imgLogo;
    private TextView txtVersion;
    private Thread sthread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);
        imgLogo=findViewById(R.id.imgLogo);
        txtVersion=findViewById(R.id.txtVersion);
        startAnimation();
    }

    private void startAnimation() {
        sthread=new Thread(){
            @Override
            public void run(){
                super.run();
                int waited=0;
                while (waited<3500){
                    try {
                        sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    waited+=100;
                }
                StartActivity.this.finish();
                Intent intent=new Intent(StartActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        };
        sthread.start();
    }
}
