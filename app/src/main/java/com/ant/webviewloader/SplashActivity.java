package com.ant.webviewloader;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.ant.webviewloader.utils.AppConstants;
import com.ant.webviewloader.utils.SpUtils;
import com.ant.webviewloader.utils.ViewContrl;
import com.lotteryx.app.WelcomeGuideActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 判断是否是第一次开启应用
        boolean isFirstOpen = SpUtils.getBoolean(this, AppConstants.FIRST_OPEN);

        // 如果是第一次启动，则先进入功能引导页
        if (!isFirstOpen) {
            Intent intent =  new Intent(this, WelcomeGuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // 如果不是第一次启动app，则正常显示启动屏
        setContentView(R.layout.activity_splash);

        /*new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                enterHomeActivity();
            }
        }, 3000);*/

        ViewContrl.setNotify(new ViewContrl.Notify() {
            @Override
            public void onPost() {
                finish();
            }
        });

    }

    private void enterHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewContrl.setNotify(null);
    }
}
