package com.bawie.zhangqingling1506a0807;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/8/1614:09
 */

public class ThreeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView fanhui;
    private WebView webView;
    private ProgressBar bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threeactivity);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        fanhui = (ImageView) findViewById(R.id.fanhui);
        webView = (WebView) findViewById(R.id.webView);
        bar = (ProgressBar) findViewById(R.id.progress);
        fanhui.setOnClickListener(this);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                bar.setProgress(newProgress);
            }
        });
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}
