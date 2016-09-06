package com.example.weshare.utils;

import com.example.weshare.databean.UpdateBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-06
 * Time: 10:41
 * For:
 */

public interface HttpService
{
    @GET("mobile/aboutapk.php")
    Call<UpdateBean> getUpdateInfo();//获取应用更新信息


}
