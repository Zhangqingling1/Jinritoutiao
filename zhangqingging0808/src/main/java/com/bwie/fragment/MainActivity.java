package com.bwie.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mylist;
    private ImageView myimg;
    private ImageView myimg2;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        mylist = (ListView) findViewById(R.id.mylist);
        myimg = (ImageView) findViewById(R.id.myimg);
        myimg2 = (ImageView) findViewById(R.id.myimg2);
        list = new ArrayList<>();
        list.add("帧布局动画");
        list.add("屏幕中间到屏幕下方");
        list.add("半透明到完全显示");
        list.add("本身大小3倍动画");
        list.add("左侧移除动画");
        mylist.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list));
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private AnimationDrawable drawable;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        myimg2.setVisibility(View.GONE);
                        myimg.setVisibility(View.VISIBLE);
                        myimg.setImageResource(R.drawable.myanim);
                        drawable = (AnimationDrawable)myimg.getDrawable();
                        drawable.start();
                        break;
                    case 1:
                        myimg.setVisibility(View.GONE);
                        myimg2.setVisibility(View.VISIBLE);
                        TranslateAnimation tr=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,2f);
                        tr.setDuration(3000);
                        myimg2.startAnimation(tr);
                        break;
                    case 2:
                        myimg.setVisibility(View.GONE);
                        myimg2.setVisibility(View.VISIBLE);
                        AlphaAnimation al=new AlphaAnimation(0.5f,1f);
                        al.setDuration(3000);
                        myimg2.startAnimation(al);
                        break;
                    case 3:
                        myimg.setVisibility(View.GONE);
                        myimg2.setVisibility(View.VISIBLE);
                        ScaleAnimation ro=new ScaleAnimation(Animation.RELATIVE_TO_SELF,3,Animation.RELATIVE_TO_SELF,3);
                        ro.setDuration(3000);
                        myimg2.startAnimation(ro);
                        break;
                    case 4:
                        myimg.setVisibility(View.GONE);
                        myimg2.setVisibility(View.VISIBLE);
                        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.myset);
                        myimg2.startAnimation(animation);
                        break;
                }
            }
        });
    }
}
