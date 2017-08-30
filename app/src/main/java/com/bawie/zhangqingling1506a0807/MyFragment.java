package com.bawie.zhangqingling1506a0807;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.limxing.xlistview.view.XListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/8/78:53
 */

public class MyFragment extends Fragment implements XListView.IXListViewListener {
    private String text;
    private InfoUtils infoUtils;
    private List<InfoUtils.ResultBean.ListBean> list;
    private MyBase base;
    private XListView xListView;
    private boolean flag;
    private int index = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        text = bundle.getString("text");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment,container,false);
//        TextView text1 = (TextView) view.findViewById(R.id.TV);
//        text1.setText(text);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        xListView = (XListView) view.findViewById(R.id.XLV);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
    }

    public void data(){

        RequestParams params = new RequestParams("http://api.jisuapi.com/news/get");
        params.addQueryStringParameter("appkey","f8aad9299757d186");
        params.addQueryStringParameter("channel","头条");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();
                infoUtils = gson.fromJson(result, InfoUtils.class);
                list = infoUtils.getResult().getList();
                if (base==null){
                    base = new MyBase(getActivity(),list,xListView);
                    xListView.setAdapter(base);
                }else {

                    base.add(getActivity(),list,flag);
                    base.notifyDataSetChanged();
                }

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
        });

    }

    @Override
    public void onRefresh() {
        ++index;
        data();
        flag=true;
        xListView.stopRefresh(true);
    }

    @Override
    public void onLoadMore() {
        ++index;
        data();
        flag=false;
        xListView.stopLoadMore();
    }
}
