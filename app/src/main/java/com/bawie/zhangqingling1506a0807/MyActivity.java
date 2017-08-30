package com.bawie.zhangqingling1506a0807;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/8/2214:07
 */

public class MyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private List<String> list;
    private String size;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity);
        listView = (ListView) findViewById(R.id.LV);
        listView.setOnItemClickListener(this);
        list = new ArrayList<>();
        list.add("列表显示摘要");
        list.add("字体大小");
        list.add("列表页评论");
        list.add("非WiFi网络流量");
        try {
            size = getTotalCacheSize();

        } catch (Exception e) {
            e.printStackTrace();
        }
        list.add("缓存大小"+"             "+size);
        list.add("清理缓存");
        list.add("推送通知");
        list.add("互动插件");
        list.add("自动优化阅读");
        list.add("收藏时转发");

        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, list));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                String[] strings = {"最佳效果", "较省流量", "极省流量"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("非WiFi下载，请注意");
                builder.setNegativeButton("取消",null);
                int mode = App.appcontext().getSharedPreferences(NetUtils.BAOCUN, Context.MODE_PRIVATE).getInt(NetUtils.KEY, 0);

                builder.setSingleChoiceItems(strings,mode, new DialogInterface.OnClickListener() {
                    @Override
                    public
                    void onClick(DialogInterface dialog, int which) {
                        App.appcontext().getSharedPreferences(NetUtils.BAOCUN,Context.MODE_PRIVATE).edit().putInt(NetUtils.KEY,which).commit();

                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            case 4:
                break;
            case 5:
                clearAllCache(MyActivity.this);
                String size = null;
                try {
                    size = getTotalCacheSize();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
        }
    }
    /**
     * 计算app的缓存大小其实计算的是 getCacheDir()这个file和getExternalCacheDir()这个file大小的和
     */
    public String getTotalCacheSize() throws Exception {
        long cacheSize = getFolderSize(this.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(this.getExternalCacheDir());
        }
//        File cacheDir = StorageUtils.getOwnCacheDirectory(this, "universalimageloader/Cache");
        return getFormatSize(cacheSize);
    }

    /**
     * 计算文件夹的大小
     */
    public static long getFolderSize(File file) throws Exception {
        if (!file.exists()) {
            return 0;
        }
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化得到的总大小 默认是byte,  然后根据传入的大小,自动转化成合适的大小单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }


    /**
     * 清理app的缓存 其实是清除的getCacheDir 和getExternalCacheDir这两个文件
     *
     * @param context
     */
    public static void clearAllCache(Context context) {

        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    /**
     * 删除一个文件夹
     *
     * @param dir
     * @return
     */
    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
