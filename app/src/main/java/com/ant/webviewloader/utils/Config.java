package com.ant.webviewloader.utils;

import cn.bmob.v3.BmobObject;

/**
 * Created by czh on 7/11/2017.
 */

public class Config extends BmobObject {

    private String url;
    private String type;
    private boolean show;
    private String appid;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getAppId() { return appid; }

    public void setShow(String appid) {
        this.appid = appid;
    }

}
