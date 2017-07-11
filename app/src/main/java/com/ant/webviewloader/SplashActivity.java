package com.ant.webviewloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ant.webviewloader.utils.ViewContrl;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ViewContrl.setNotify(new ViewContrl.Notify() {
            @Override
            public void onPost() {
                finish();
            }
        });

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                finish();
//            }
//        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewContrl.setNotify(null);
    }
}
