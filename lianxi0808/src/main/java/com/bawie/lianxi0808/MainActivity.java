package com.bawie.lianxi0808;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private connactivity receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new connactivity();
        registerReceiver(receiver,intentFilter);

    }
    public void click(View view){
        RequestParams params = new RequestParams("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8F%AF%E7%88%B1%E5%9B%BE%E7%89%87&hs=2&pn=1&spn=0&di=39620268820&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=890732425%2C3078577554&os=937466599%2C3747629173&simid=3360953015%2C208184803&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=0&oriquery=%E5%8F%AF%E7%88%B1%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fimg1.3lian.com%2Fimg013%2Fv3%2F2%2Fd%2F61.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bnstwg_z%26e3Bv54AzdH3F2tuAzdH3Fda8nAzdH3Fa9-dnAzdH3Fnc8mb_z%26e3Bip4s&gsm=0");
       //设置缓存路径
        params.setSaveFilePath(getExternalCacheDir().getAbsolutePath()+"/image");
        Log.e("tag", "=====================" + getExternalCacheDir().getAbsolutePath());
        //设置断点缓存
        params.setAutoRename(true);
        //设置线程池
        params.setExecutor(new PriorityExecutor(3,true));

        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {

                Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {

            }
        });

    }

    //网络判断
    public class connactivity extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){

                ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = manager.getActiveNetworkInfo();
                if (info!=null&&info.isConnected()){
                    if (ConnectivityManager.TYPE_WIFI==info.getType()) {
                        Toast.makeText(context, "wifi连接，可放心使用", Toast.LENGTH_SHORT).show();
                    }else if (ConnectivityManager.TYPE_MOBILE==info.getType()) {
                        Toast.makeText(context, "当前使用移动连接，请谨慎使用", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "网络不可用，请设置网络", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, "网络不可用，请设置网络", Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
