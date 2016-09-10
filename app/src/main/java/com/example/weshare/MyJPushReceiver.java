package com.example.weshare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-09
 * Time: 19:38
 * For:
 */

public class MyJPushReceiver extends BroadcastReceiver
{
    public static final String ACTION = "com.jpush.receive";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle extras = intent.getExtras();
        //收到通知栏
        if(JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(action)){
            //获得通知栏中的文字
            String alert = extras.getString(JPushInterface.EXTRA_ALERT);
            Toast.makeText(context, "收到服务器推送消息:\n" + alert, Toast.LENGTH_SHORT).show();
            //把内容通过广播发给MainActivity
            Intent intent1 = new Intent(ACTION);
            intent1.putExtra("content",alert);
            context.sendBroadcast(intent1);
        }
    }
}
