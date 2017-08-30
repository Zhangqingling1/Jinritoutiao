package com.bawie.zhangqingling1506a0807;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.andy.library.ChannelBean;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tab;
    private ViewPager pager;
    private ImageView image;
    private SlidingMenu slidingMenu1,slidingMenu2;
    private ImageView image1;
    private ImageView moshi;
    private ImageView shouji;
    private ImageView shezhi1;
    private ConnectivityBroadcastReceiver mConnectivityBroadcastReceiver;
    private ImageView pindao;
    private List<ChannelBean> bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Themeutils.onactivitycreate(this);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mConnectivityBroadcastReceiver = new ConnectivityBroadcastReceiver();
        registerReceiver(mConnectivityBroadcastReceiver, filter);

        tab = (TabLayout) findViewById(R.id.TL);
        pager = (ViewPager) findViewById(R.id.VP);
        //设置pager布局加载几个，以防出现预加载
        pager.setOffscreenPageLimit(11);
        image = (ImageView) findViewById(R.id.IV);
        pindao = (ImageView) findViewById(R.id.image_view1);
        pindao.setOnClickListener(this);
        image.setOnClickListener(this);
        tab.setupWithViewPager(pager);
        pager.setAdapter(new Mypager(getSupportFragmentManager()));

        slidingMenu1 = new SlidingMenu(this);
        slidingMenu1.setMode(SlidingMenu.LEFT);
        slidingMenu1.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu1.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        slidingMenu1.setBehindOffset(200);
        slidingMenu1.setFadeDegree(1f);
        slidingMenu1.setMenu(R.layout.menu);
        image1 = (ImageView) slidingMenu1.findViewById(R.id.IV2);
        shouji = (ImageView) slidingMenu1.findViewById(R.id.IV4);
        shezhi1 = (ImageView) slidingMenu1.findViewById(R.id.shezhi);
        shouji.setOnClickListener(this);
        shezhi1.setOnClickListener(this);

        slidingMenu2 = new SlidingMenu(this);
        slidingMenu2.setMode(SlidingMenu.RIGHT);
        slidingMenu2.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenu2.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        slidingMenu2.setBehindOffset(200);
        slidingMenu2.setFadeDegree(1f);
        slidingMenu2.setMenu(R.layout.menu2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.IV:
                slidingMenu1.toggle();
                break;
            case R.id.IV4:
                Intent intent = new Intent(MainActivity.this,Twoactivity.class);
                startActivity(intent);
                break;
            case R.id.image_view1:
//                bean = new ArrayList<>();
//                ChannelBean bean1;
//                for (int i = 0; i < 20; i++) {
//
//                    if (i<10){
//                        bean1 = new ChannelBean("频道"+i,true);
//                    }else {
//                        bean1 = new ChannelBean("频道"+i,false);
//                    }
//                    bean.add(bean1);
//                }
//                ChannelActivity.startChannelActivity(this,bean);
                slidingMenu2.toggle();
                break;
            case R.id.shezhi:
               Intent intent1 = new Intent(MainActivity.this,MyActivity.class);
                startActivity(intent1);
                break;

        }
        image1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);

            }

        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();

            String i = data.get("iconurl");
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)                               //启用内存缓存
                    .cacheOnDisk(true)                                 //启用外存缓存
                    .displayer(new CircleBitmapDisplayer())
                    .build();
            ImageLoader.getInstance().displayImage(i,image1,options);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
    /**
     * 接受网络状态的改变
     */
    public class ConnectivityBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {

                boolean isMobileConnectivity = true;

                //如果能走到这，说明网络已经发生变化
                ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    if (ConnectivityManager.TYPE_WIFI == activeNetworkInfo.getType()) {
                        Toast.makeText(MainActivity.this, "当前wifi模式", Toast.LENGTH_SHORT).show();
                        isMobileConnectivity = false;
                        //移动网络
                    } else if (ConnectivityManager.TYPE_MOBILE == activeNetworkInfo.getType()) {
                        Toast.makeText(MainActivity.this, "当前移动网络，请注意", Toast.LENGTH_SHORT).show();
                        isMobileConnectivity = true;
                        //获得现在的网络状态 是移动网络，去改变我们的访问接口

                    } else {
                        Toast.makeText(MainActivity.this, "网络不可用，请检查网络", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "网络不可用，请检查网络", Toast.LENGTH_SHORT).show();
                }

                NetUtils.danli().changeNetState(isMobileConnectivity);
            }
        }
    }

    public void dianji(View view){
        Themeutils.ontiaozhuan(this);
    }

}
