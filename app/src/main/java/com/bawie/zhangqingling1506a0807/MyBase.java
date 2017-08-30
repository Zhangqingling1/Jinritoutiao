package com.bawie.zhangqingling1506a0807;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.limxing.xlistview.view.XListView;

import org.xutils.x;

import java.util.List;

import static com.bawie.zhangqingling1506a0807.R.id.pop;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/8/1420:39
 */

public class MyBase extends BaseAdapter {
    Context context;
    List<InfoUtils.ResultBean.ListBean> list;
    private final LayoutInflater inflater;
    private PopupWindow popwindow;
    private View viewpop;
    private PopupWindow popupWindow;
    private TextView delete;
    private ImageView colse;
    private XListView xListView;


    public MyBase(Context context, List<InfoUtils.ResultBean.ListBean> list,XListView xListView) {
        this.context = context;
        this.list = list;
        this.xListView = xListView;
        inflater = LayoutInflater.from(context);
        initpop();
    }
    public void add(Context context, List<InfoUtils.ResultBean.ListBean> list1,boolean flag){
        for (InfoUtils.ResultBean.ListBean bean: list1) {
            if (flag){
                list.add(0,bean);
            }else {
                list.add(bean);
            }
        }

    }

    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.fragment_layout,null);
            holder.name = (TextView) convertView.findViewById(R.id.text_view1);
            holder.type = (TextView) convertView.findViewById(R.id.text_view2);
            holder.area = (TextView) convertView.findViewById(R.id.text_view3);
            holder.image = (ImageView) convertView.findViewById(R.id.image_view);
            holder.more = (ImageView) convertView.findViewById(pop);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getSrc());
        holder.type.setText(list.get(position).getTitle());
        holder.area.setText(list.get(position).getTime());
        holder.more.setOnClickListener(new Popaction(position));
        x.image().bind(holder.image,list.get(position).getPic());
        //跳转页面 显示新闻详情
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(context,ThreeActivity.class);
                intent.putExtra("url",list.get(position-1).getUrl());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class Popaction implements View.OnClickListener{
        private int position;

        public Popaction(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            int[] array = new int[2];
            v.getLocationOnScreen(array);
            int x = array[0];
            int y = array[1];
            showpop(v,position,x,y);
        }
    }
    public void initpop(){

        viewpop = inflater.inflate(R.layout.item,null);
        popupWindow = new PopupWindow(viewpop, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        delete = (TextView) viewpop.findViewById(R.id.delete_tv);
        colse = (ImageView) viewpop.findViewById(R.id.close_iv);
    }
    public void showpop(View view, final int position, int x, int y){

        popupWindow.showAtLocation(view,0,x,y);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        colse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (popupWindow.isShowing()){

                    popupWindow.dismiss();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
                if (popupWindow.isShowing()){

                    popupWindow.dismiss();
                }
            }
        });

    }
    class ViewHolder{
        private TextView name;
        private TextView type;
        private TextView area;
        private ImageView image;
        private ImageView more;
    }
}
