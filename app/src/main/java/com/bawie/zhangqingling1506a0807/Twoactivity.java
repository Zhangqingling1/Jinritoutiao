package com.bawie.zhangqingling1506a0807;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/8/1014:56
 */

public class Twoactivity extends AppCompatActivity implements View.OnClickListener {
    private Button but1,but2;
    private EditText ed1,ed2;
    private EventHandler eh = new EventHandler(){

        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Twoactivity.this, "验证成功", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Twoactivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    //提交验证码成功
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //获取验证码成功

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Twoactivity.this, "获取认证码成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表
                }
            }else{
                ((Throwable)data).printStackTrace();
                Log.e("tag", ((Throwable) data).getMessage().toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Twoactivity.this, "获取失败", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Themeutils.onactivitycreate(this);
        setContentView(R.layout.duanxin);
        but1 = (Button) findViewById(R.id.yanzhengma);
        but2 = (Button) findViewById(R.id.yanzheng);
        ed1 = (EditText) findViewById(R.id.phone);
        ed2 = (EditText) findViewById(R.id.ET2);
        but1.setOnClickListener(this);
        but2.setOnClickListener(this);

        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yanzhengma:
                SMSSDK.getVerificationCode("86", ed1.getText().toString().trim(), new OnSendMessageHandler() {
                    @Override
                    public boolean onSendMessage(String s, String s1) {
                        return false;
                    }
                });
                break;
            case R.id.yanzheng:
                SMSSDK.submitVerificationCode("86", ed1.getText().toString().trim(), ed2.getText().toString().trim());
                break;
        }



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
}
