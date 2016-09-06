package com.example.weshare.utils.appdownloader;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weshare.R;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by: Daniel Qin
 * Date: 2016-08-15
 * Time: 10:14
 * For:
 */

public class DownLoadTask implements Runnable
{

    private Notification.Builder mBuilder;
    private Context mContext;
    private NotificationManager mNotificationManager;
    private int task_id;
    private String apk_name;

    private String path;
    private TextView mTextView;
    private File filePath;

    public DownLoadTask(Context context, String path, String appname, TextView textView,int id)
    {
        mContext = context;
        mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        this.apk_name = (appname == null ? "  " : appname);
        this.path = path;
        if (textView == null)
        {
            mTextView = new TextView(context);
        }
        else
        {
            this.mTextView = textView;
        }
        this.mTextView.setTag(path);
        this.task_id = id;
        String appName = path.substring(path.lastIndexOf("/"));
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        this.filePath = new File(externalStoragePublicDirectory + appName);
    }

    private Handler sHandler = new Handler()
    {
        @Override
        public void handleMessage(Message message)
        {
            switch (message.what)
            {
                case 0:
                    intall((File) message.obj);
                    break;
                case 1:
                    if (mTextView != null)
                    {
                        mTextView.setText("正在下载");
                        mTextView.setBackgroundColor(Color.GREEN);
                        mTextView.setClickable(false);
                    }
                    break;
                case 2:
                    if (mTextView != null)
                    {
                        if (path.equals(mTextView.getTag()))
                        {
                            mTextView.setText("已经下载");
                            mTextView.setBackgroundColor(Color.RED);
                            mTextView.setClickable(true);
                        }
                    }
                    break;
            }
        }
    };

    private void showNotification()
    {
        mBuilder = new Notification.Builder(mContext);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("正在下载...");
        Notification notification = mBuilder.getNotification();
        mNotificationManager.notify(this.task_id, notification);
    }



    private void close(Closeable closeable)
    {
        if (closeable != null)
        {
            try
            {
                closeable.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void intall(File file)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        mContext.startActivity(intent);

    }

    @Override
    public void run()
    {
        if (filePath.exists())
        {
            Looper.prepare();
            Toast.makeText(mContext, apk_name + "已经下载!正在为您启动安装程序!", Toast.LENGTH_SHORT).show();
            intall(this.filePath);
            Looper.loop();
            return;
        }
        showNotification();
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try
        {
            URL url = new URL(path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(3000);
            urlConnection.connect();
            int totallenth = urlConnection.getContentLength();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                fileOutputStream = new FileOutputStream(filePath);
                sHandler.sendEmptyMessage(1);
                inputStream = urlConnection.getInputStream();
                int len = 0;
                byte[] buffer = new byte[1024];
                int currentlen = 0;

                while ((len = inputStream.read(buffer)) != - 1)
                {
                    fileOutputStream.write(buffer, 0, len);
                    currentlen += len;
                    int currentprogress = (int) (currentlen * 100 / totallenth);
                    mBuilder.setProgress(100, currentprogress, false);
                    mBuilder.setContentTitle("正在下载" + apk_name);
                    mBuilder.setContentText("已下载" + currentprogress + "%,请稍后...");
                    mNotificationManager.notify(this.task_id, mBuilder.getNotification());
                }
                fileOutputStream.flush();

                mBuilder.setProgress(100, 100, false);
                mBuilder.setContentTitle("下载提示");
                mBuilder.setContentText(apk_name + "下载完成!");
                sHandler.sendEmptyMessage(2);
//                        mBuilder.getNotification().flags = Notification.FLAG_AUTO_CANCEL|Notification.DEFAULT_SOUND;
                mNotificationManager.notify(this.task_id, mBuilder.getNotification());
//                        Toast.makeText(MainActivity.this, "安装包下载完成!正在启动安装程序!", Toast.LENGTH_SHORT).show();
                Message message = sHandler.obtainMessage();
                message.what = 0;
                message.obj = this.filePath;
                message.sendToTarget();
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(inputStream);
            close(fileOutputStream);
        }
    }
}
