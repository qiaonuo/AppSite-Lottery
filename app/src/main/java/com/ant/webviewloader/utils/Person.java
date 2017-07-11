package com.ant.webviewloader.utils; /**
 * Created by czh on 7/10/2017.
 */

import cn.bmob.v3.BmobObject;

public class Person extends BmobObject {
    private String name;
    private String address;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
