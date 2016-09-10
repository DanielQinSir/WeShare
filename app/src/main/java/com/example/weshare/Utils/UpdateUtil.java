package com.example.weshare.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.weshare.databean.UpdateBean;
import com.example.weshare.utils.appdownloader.APPDownloader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by: Daniel Qin
 * Date: 2016-08-15
 * Time: 13:25
 * For:
 */

public class UpdateUtil
{

    private static Context mContext;
    private static UpdateBean sUpdateBean;
    private final static int CURRENT_VERSION_CODE = 39;

    public static void checkForUpdateBean(Context context)
    {
        mContext = context;
       HttpServiceUtil.init().getUpdateInfo().enqueue(new Callback<UpdateBean>()
       {
           @Override
           public void onResponse(Call<UpdateBean> call, Response<UpdateBean> response)
           {
               sUpdateBean = response.body();
               if (sUpdateBean != null)
               {
                   if (sUpdateBean.getApkVerCode() != CURRENT_VERSION_CODE)
                   {
                       showDialog();
                   }
               }
           }

           @Override
           public void onFailure(Call<UpdateBean> call, Throwable t)
           {
               Toast.makeText(mContext, "您的网络连接可能存在问题!", Toast.LENGTH_SHORT).show();
           }
       });
    }

    private static void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("发现新版本,请更新后使用!");
        builder.setMessage("最新版:" + sUpdateBean.getApkVersion() + "\n" + sUpdateBean.getApkContent());
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(mContext, "正在后台为您下载最新版本!\n您可以在通知栏查看进度信息!", Toast.LENGTH_SHORT).show();
                APPDownloader.init().downLoad(mContext,sUpdateBean.getApkDownloadUrl(),"享享",null);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("下次再说", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        });
        Dialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
}
