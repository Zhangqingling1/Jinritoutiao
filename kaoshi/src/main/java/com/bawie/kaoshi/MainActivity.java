package com.bawie.kaoshi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        image = (ImageView) findViewById(R.id.IV);
//        image.setImageResource(R.mipmap.a);
//        image.setImageResource(R.mipmap.b);
//        image.setImageResource(R.mipmap.c);
//        image.setImageResource(R.mipmap.d);
//        image.setImageResource(R.mipmap.e);
//        image.setImageResource(R.mipmap.f);
//        image.setImageResource(R.mipmap.g);
//        image.setImageResource(R.mipmap.h);

        Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(1000);
        image.startAnimation(animation);
    }
}
