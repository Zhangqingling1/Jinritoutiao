package com.bawie.zhangqingling1506a0807;

import android.content.Context;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/8/1016:10
 */

public class NetUtils {

    public static final String BAOCUN = "BAOCUN";
    private static String TU1="大图显示";
    private static  String TU2="中图显示";
    private static  String TU3="小图实现";

    private static NetUtils netUtils;
    public  static String KEY="KEY";
    private static String MOREN=TU1;
    private static boolean isMobileConnectivity = true;

    private NetUtils(){

    }
    //单例模式
    public static NetUtils danli(){
        if (netUtils==null){
            synchronized (NetUtils.class){
                if (netUtils==null){
                    netUtils = new NetUtils();
                }
            }
        }
        return netUtils;
    }
    public static String moshi(){

        if (isMobileConnectivity){

            int mode = App.appcontext().getSharedPreferences(BAOCUN, Context.MODE_PRIVATE).getInt(KEY, 0);
            switch (mode){

                case 0:
                    MOREN = TU1;
                break;
                case 1:
                    MOREN = TU2;
                break;
                case 2:
                    MOREN = TU3;
                break;
            }
        }else {
            MOREN=TU1;
        }
        
        return MOREN;
    }



    public void changeNetState(boolean isMobileConnectivity) {
        this.isMobileConnectivity = isMobileConnectivity;
    }
}
