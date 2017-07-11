package com.ant.webviewloader.utils;

/**
 * Created by ant on 16/9/20.
 */

public class ViewContrl {
    public static Notify mNotify;

    public static void post() {
        if (mNotify != null) {
            mNotify.onPost();
        }
    }

    public static void setNotify(Notify notify) {
        mNotify = notify;
    }

    public interface Notify {
        void onPost();
    }
}
