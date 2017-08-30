package com.bawie.lianxi0808;

import android.app.Application;

import org.xutils.x;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/8/820:08
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
