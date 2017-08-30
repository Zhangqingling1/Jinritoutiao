package com.bawie.zhangqingling1506a0807;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/8/915:55
 */

public class Themeutils {
    private static int THEME = 0;
    private static final int THEME_ZAO=0;
    private static final int THEME_WAN=1;

    public static void onactivitycreate(Activity activity){
        switch (THEME){
            case THEME_ZAO:
                activity.setTheme(R.style.Zao);
                break;
            case THEME_WAN:
                activity.setTheme(R.style.Wan);
                break;
        }
    }
    public static void ontiaozhuan(Activity activity){
        switch (THEME){
            case THEME_ZAO:
                THEME=THEME_WAN;
                break;
            case THEME_WAN:
                THEME=THEME_ZAO;
                break;
        }
        activity.finish();
        activity.overridePendingTransition(R.anim.myqie,R.anim.myhuan);
        activity.startActivity(new Intent(activity,activity.getClass()));
    }

}
