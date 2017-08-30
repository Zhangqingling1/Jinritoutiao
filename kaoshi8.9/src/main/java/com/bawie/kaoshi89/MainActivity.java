package com.bawie.kaoshi89;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private HttpURLConnection connection;
    private URL url;
    private Infobean fromJson;
    private List<Infobean.ResultBean.DataBean> data;
    private Sqliteutils dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new Sqliteutils(this);
        data("http://apis.juhe.cn/cook/query.php");

    }
    public void data(String Path){
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s!=null){

                    Gson gson = new Gson();
                    fromJson = gson.fromJson(s, Infobean.class);
                    data = fromJson.getResult().getData();
                    String a = data.get(0).getImtro();
                    Toast.makeText(MainActivity.this, "結果:"+data.get(0).getImtro(), Toast.LENGTH_SHORT).show();
                    dao.add(data.get(0).getImtro());
                }
            }

            @Override
            protected String doInBackground(String... params) {
                String param = params[0];
                try {
                    url = new URL(param);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(500);
                    connection.setReadTimeout(500);
                    OutputStream stream = connection.getOutputStream();
                    stream.write(("key="+"e5024b374ee1d772bf9101d04614f6c1&menu="+ URLEncoder.encode("地三鲜","utf-8")).getBytes());
                int code = connection.getResponseCode();
                if (code==HttpURLConnection.HTTP_OK){
                    InputStream is = connection.getInputStream();
                    String json = Infoutils.json(is);
                    return json;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
                return null;
            }
        }.execute(Path);
    }
}
