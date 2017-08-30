package com.bawie.lianxi0809;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/8/915:14
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
