package com.example.weshare.utils;

import com.example.weshare.databean.AboutXXBean;
import com.example.weshare.databean.ClubBean;
import com.example.weshare.databean.UpdateBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-06
 * Time: 10:41
 * For:
 */

public interface HttpService
{
    @POST("mobile/control/member/save_token.php")//保存
    Call<ResponseBody> saveToken(@Query("token")String token,@Query("type")int type,@Query("sid")String sid);

    @POST("mobile/control/forum/index_27.php")//论坛
    Call<ResponseBody> forum(@Query("sid")String sid);

    @FormUrlEncoded
    @POST("mobile/location.php")//地址信息1
    Call<ResponseBody> location(@Query("submit")String submit,@Query("sid")String sid,@Query("areaid")int areaid,@Query("zoneid")int zoneid,@Query("streetid")int streetid);

    @POST("mobile/control/index/start_advs.php")//开始广告2
    Call<ResponseBody> startad(@Query("sid")String sid);

    @POST("mobile/aboutapk.php")//检测更新3
    Call<UpdateBean> getUpdateInfo();

    @POST("mobile/control/index/getProInfo.php")//获取商品信息6
    Call<ResponseBody> getProductInfo(@Query("submit")String submit,@Query("sid")String sid);

    @POST("mobile/control/index/getProInfo.php")//获取商品信息2 7
    Call<ResponseBody> getProductInfo2(@Query("submit")String submit,@Query("sid")String sid);

    @POST("mobile/control/index/advs.php")//获取广告5
    Call<ResponseBody> getAds(@Query("sid")String sid);

    @POST("mobile/control/index/special_area_all.php")//获取特供信息4
    Call<ResponseBody> specialArea(@Query("submit")String submit,@Query("sid")String sid);

    @POST("mobile/control/forum/index_27.php")//获取俱乐部模块信息
    Call<ClubBean> getClubInfo(@Query("sid")String sid);

    @FormUrlEncoded
    @POST("mobile/apkdesc.php")//关于享享
    Call<AboutXXBean> aboutXX(@Field("submit")String submit, @Field("sid")String sid);
}
