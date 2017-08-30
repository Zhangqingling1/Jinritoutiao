package com.bawie.kaoshi0812;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xieru(View view) {
        write();
    }

    public void write() {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                File file = new File(Environment.getExternalStorageDirectory(), "aaa.txt");

                OutputStream os = new FileOutputStream(file);
                os.write("高薪就业，万新就业".getBytes());
                os.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void duqu(View view) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "aaa.txt");
            InputStream is = new FileInputStream(file);

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String msg = reader.readLine();

            reader.close();
            Toast.makeText(this, "结果:"+msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

