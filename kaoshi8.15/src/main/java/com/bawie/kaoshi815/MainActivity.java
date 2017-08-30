package com.bawie.kaoshi815;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Infobean infobean;
    private List<Infobean.ResultBean.DataBean.StepsBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.LV);
        try {
            data("http://apis.juhe.cn/cook/query.php?key=e5024b374ee1d772bf9101d04614f6c1&menu="+ URLEncoder.encode("面","utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



    public void data(String path){
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s!=null){
                    Gson gson = new Gson();
                    infobean = gson.fromJson(s, Infobean.class);
                    list = infobean.getResult().getData().get(0).getSteps();
                    listView.setAdapter(new MyBase(list));
                }
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String path = params[0];
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    int code = connection.getResponseCode();
                    if (code==HttpURLConnection.HTTP_OK){
                        InputStream is = connection.getInputStream();
                        String json = Infoutils.readFromNetWork(is);
                        return json;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(path);
    }
    public void data1(String Path,final ImageView image){
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {

                if (bitmap!=null){
                    image.setImageBitmap(bitmap);
                }else {
                    image.setImageResource(R.mipmap.ic_launcher);
                }
            }

            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    String path = params[0];
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    int code = connection.getResponseCode();
                    if (code==HttpURLConnection.HTTP_OK){
                        InputStream is = connection.getInputStream();
                        return BitmapFactory.decodeStream(is);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(Path);
    }
    class MyBase extends BaseAdapter{

        List<Infobean.ResultBean.DataBean.StepsBean> list;

        public MyBase(List<Infobean.ResultBean.DataBean.StepsBean> list) {
            this.list = list;
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
            if (convertView==null){
                convertView = convertView.inflate(MainActivity.this, R.layout.item, null);
                ImageView image = (ImageView) convertView.findViewById(R.id.IV);
                TextView text = (TextView) convertView.findViewById(R.id.textView);
                text.setText(list.get(position).getStep());
                data1(list.get(position).getImg(),image);
            }
            return convertView;
        }
    }
}
