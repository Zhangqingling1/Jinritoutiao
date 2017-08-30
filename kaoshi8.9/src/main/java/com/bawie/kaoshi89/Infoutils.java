package com.bawie.kaoshi89;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/8/99:06
 */

public class Infoutils {
    public static String json(InputStream is){
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len=is.read(b))!=-1){
                stream.write(b,0,len);
            }
            is.close();
            return stream.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
