package com.bawie.kaoshi0816;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private HttpURLConnection connection;
    private InputStream is;
    private ImageView image;
    private Button but1,but2,but3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.IV);
        but2 = (Button) findViewById(R.id.but2);
        but2.setOnClickListener(this);
        data("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1502845117&di=82061375d40d5c1b88b1c641b919a67a&src=http://img4.duitang.com/uploads/item/201601/26/20160126004851_JehQk.jpeg",image);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .displayer(new CircleBitmapDisplayer())
                .build();
        ImageLoader.getInstance().displayImage("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1502845117&di=82061375d40d5c1b88b1c641b919a67a&src=http://img4.duitang.com/uploads/item/201601/26/20160126004851_JehQk.jpeg",image,options);
    }
    public void data(String path, final ImageView image){

        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
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
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    int code = connection.getResponseCode();
                    if (code==HttpURLConnection.HTTP_OK){
                        is = connection.getInputStream();
                        return BitmapFactory.decodeStream(is);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute(path);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.but2:
                ImageLoader.getInstance().clearMemoryCache();
                ImageLoader.getInstance().clearDiskCache();
                break;
        }
    }
}

