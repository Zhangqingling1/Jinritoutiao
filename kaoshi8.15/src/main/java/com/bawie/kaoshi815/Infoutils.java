package com.bawie.kaoshi815;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/8/159:14
 */

public class Infoutils {
    public static String readFromNetWork(InputStream is) throws Exception{


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = is.read(buffer)) != -1){

            baos.write(buffer,0,len);
        }

        is.close();
        baos.close();

        return baos.toString();


    }
}
