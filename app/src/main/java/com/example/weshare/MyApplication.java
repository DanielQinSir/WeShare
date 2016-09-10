package com.example.weshare;

import android.app.Application;

import com.example.weshare.databean.UserBean;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-09
 * Time: 19:43
 * For:
 */

public class MyApplication extends Application
{
    public static UserBean sUser;

    @Override
    public void onCreate()
    {
        super.onCreate();
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);
        Set<String> set = new HashSet<>();
        set.add("运动");
        set.add("美女");
        set.add("IT");
        //添加设备标签和别名
        JPushInterface.setAliasAndTags(this,"newUser",set);
    }
}
