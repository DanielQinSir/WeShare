package com.example.weshare.utils.appdownloader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by: Daniel Qin
 * Date: 2016-08-18
 * Time: 13:42
 * For:
 */

public class APPDownloader
{

    private static APPDownloader appdownloader;
    private static ExecutorService executorService;
    private int task_id;

    public APPDownloader(int task_id)
    {
        this.task_id = task_id;
    }

    public static APPDownloader init()
    {
        if (executorService == null)
        {
            executorService = Executors.newFixedThreadPool(3);
        }
        if (appdownloader == null)
        {
            appdownloader = new APPDownloader(1000);
        }
        return appdownloader;
    }
    public void downLoad(Context context, String path, @Nullable String appname, @Nullable TextView textView)
    {
            executorService.execute(new DownLoadTask(context,path,appname,textView,this.task_id ++));
    }

}
