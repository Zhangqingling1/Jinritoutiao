package com.bawie.zhangqingling1506a0807;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/8/78:53
 */

public class Mypager extends FragmentPagerAdapter {
    private FragmentManager fragmentManager;
    private List<Fragment> lists;
    private String[] title = {"推荐","音乐","视频","健康","新闻","军事娱乐","音乐","视频","健康","新闻","军事娱乐"};
    public Mypager(FragmentManager fm) {
        super(fm);
        fragmentManager = fm;
    }

    public Mypager(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        fragmentManager = fm;
        lists = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text",title[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return title!=null?title.length:0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
