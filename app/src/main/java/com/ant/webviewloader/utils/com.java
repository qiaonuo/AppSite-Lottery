package com.ant.webviewloader.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by czh on 7/11/2017.
 */

public class com {

    //提示信息
    public static void ShowMsg(String title,String msg, Context context) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setTitle(title);
        dlg.setMessage(msg);
        dlg.setPositiveButton("确定", null);
        dlg.show();
        //new test
    }

    //闪现提示
    public static void DisplayToast(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
