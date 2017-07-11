package com.ant.webviewloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.jpush.android.api.JPushInterface;

import com.ant.webviewloader.utils.ExampleUtil;
import com.ant.webviewloader.utils.MyReceiver;
import com.ant.webviewloader.utils.Person;
import com.ant.webviewloader.utils.ViewContrl;
import com.ant.webviewloader.utils.com;

import com.ant.webviewloader.utils.Config;

//import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private static final String APP_CACAHE_DIR_NAME = "/loukoo/webviewCache";
    private WebView webView;
    //private ImageView ivRefresh;
    private LinearLayout llLoading;
    //private String url = "http://lottery.apst.lqbzao.com";

    public static boolean isForeground = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //e0060c2ce7800f801fb6854ad8747a2f
        //1a133966efeb4d2d96c0336d7d03ede2
        Bmob.initialize(this, "e0060c2ce7800f801fb6854ad8747a2f");

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        startActivity(new Intent(this, SplashActivity.class));
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
//        ivRefresh = (ImageView) findViewById(R.id.iv_refresh);
        llLoading = (LinearLayout) findViewById(R.id.ll_loading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);

        setupWebView(savedInstanceState);

        registerMessageReceiver();  // used for receive msg

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        ivRefresh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                webView.reload();
//                final EditText editText = new EditText(MainActivity.this);
//                new AlertDialog.Builder(MainActivity.this).setTitle("设置网址")
//                        .setView(editText)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                url = editText.getText().toString();
//                                webView.loadUrl(url);
//                                getSharedPreferences("url", MODE_PRIVATE).edit()
//                                        .putString("url", url).apply();
//                            }
//                        })
//                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                            }
//                        })
//                        .create().show();
//            }
//        });
    }

    int count = 0;

    private void setupWebView(Bundle savedInstanceState) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //设置 缓存模式
        // 开启 DOM storage API 功能
        settings.setDomStorageEnabled(true);
        //开启 database storage API 功能
        settings.setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACAHE_DIR_NAME;
        //设置数据库缓存路径
        settings.setDatabasePath(cacheDirPath);
        //设置  Application Caches 缓存目录
        settings.setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能
        settings.setAppCacheEnabled(true);

        webView.setWebViewClient(new WebViewClient() {

           /* @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                url="http://m.loukoo.com";

                //查找Person表里面id为6b6c11c537的数据
                BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
                bmobQuery.getObject("f005f81ffa", new QueryListener<Person> () {
                    @Override
                    public void done(Person object,BmobException e) {
                        if(e==null){
                            //toast("查询成功");
                            //Toast.makeText(this,"查询成功",Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(getApplicationContext()).setMessage("查询成功").create().show();

                        }else{
                            //toast("查询失败：" + e.getMessage());
                            //Toast.makeText(this,"查询失败"+ e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return true;
            }

*/

            /* public boolean shouldOverrideUrlLoading(WebView view, String url) {


                *//*Person p2 = new Person();
        p2.setName("lucky");
        p2.setAddress("北京海淀");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    //toast("添加数据成功，返回objectId为："+objectId);
                }else{
                    //toast("创建数据失败：" + e.getMessage());
                }
            }
        });*//*


                //view.loadUrl(url);
                return true;
            }*/

          /*  @Override
            public void onLoadResource(WebView view, final String url) {



                //super.onLoadResource(view, url);
                //Log.i("url", url);
                // 此处拦截get请求
//                new AsyncHttpClient().get(url, new AsyncHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int i, PreferenceActivity.Header[] headers, byte[] bytes) {
//
//                    }
//
//                    @Override
//                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                    }
//                });
            }*/

            /*@Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                if (errorCode == 401) {
                    Toast.makeText(getApplicationContext(), "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                }
            }*/


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                llLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                llLoading.setVisibility(View.GONE);
                count++;
                Log.i("onPageFinished", "" + count);
                if (count == 2) {
                    ViewContrl.post();
                }

                if (count == 1) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ViewContrl.post();
                        }
                    }, 10 * 1000);
                }
//                if (isFirstIn) {
//                    webView.clearCache(false);
//                    clearWebViewCache();
//                    webView.reload();
//                    Log.i("re:","y");
//                    isFirstIn = false;
//                    webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
//                    webView.reload();
//                }
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                result.confirm();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });

        /*if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        } else {
            //webView.loadUrl(url);
        }*/

        //查找Person表里面id为f005f81ffa的数据
        /*BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("f005f81ffa", new QueryListener<Person>() {
            @Override
            public void done(Person object, BmobException e) {
                if (e == null) {
                    //toast("查询成功");
                    //Toast.makeText(this,"查询成功",Toast.LENGTH_SHORT).show();
                    //com.ShowMsg("这是提示框",MainActivity.this);
                    //com.DisplayToast("这是闪现提示",getBaseContext());
                    com.ShowMsg(object.getName(),MainActivity.this);

                    webView.loadUrl("http://lottery.apst.lqbzao.com");

                } else {
                    //toast("查询失败：" + e.getMessage());
                    //Toast.makeText(this,"查询失败"+ e.getMessage(),Toast.LENGTH_SHORT).show();

                    //new AlertDialog.Builder(getApplicationContext()).setMessage("查询成功").create().show();
                    webView.loadUrl("http://www.baidu.com");
                }
            }
        });*/


        BmobQuery<Config> bmobQuery = new BmobQuery<Config>();
        bmobQuery.getObject("qepY577I", new QueryListener<Config>() {
            @Override
            public void done(Config object, BmobException e) {
                if (e == null) {
                    //com.ShowMsg(object.getUrl(),MainActivity.this);
                    //com.DisplayToast("这是闪现提示",getBaseContext());

                    if(object!=null)
                    {
                        boolean shown=object.getShow();
                        String url=object.getUrl();

                        if(shown)
                        {
                            webView.loadUrl(url);
                        }
                        else
                        {
                            webView.loadUrl("http://lottery.apst.lqbzao.com");
                        }
                    }
                    else
                    {
                        webView.loadUrl("http://lottery.apst.lqbzao.com");
                    }

                } else {
                    //com.ShowMsg(object.getUrl(),MainActivity.this);
                    //com.DisplayToast("这是闪现提示",getBaseContext());
                    webView.loadUrl("http://lottery.apst.lqbzao.com");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
        webView.destroy();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("确定要退出程序吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    //for receive customer msg from jpush server

   /* public static final String MESSAGE_RECEIVED_ACTION = "com.lottery.app.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static final String ALERT="ALERT";*/

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.lottery.app.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static final String ALERT="ALERT";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    setCostomMsg(messge.toString());
                }
            } catch (Exception e){
            }
        }
    }

    private void setCostomMsg(String msg){
        if (null != msg) {
            com.ShowMsg("九州时时彩",msg.toString(),MainActivity.this);
        }
    }

}
