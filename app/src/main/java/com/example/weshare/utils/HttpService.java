package com.example.weshare.utils;

import com.example.weshare.databean.HelpBean;
import com.example.weshare.databean.StartADBean;
import com.example.weshare.databean.AboutXXBean;
import com.example.weshare.databean.ClubBean;
import com.example.weshare.databean.HomeListBean;
import com.example.weshare.databean.HomeListDetailBean;
import com.example.weshare.databean.HomeRecyclerViewBean;
import com.example.weshare.databean.HomeRecyclerViewLogoBean;
import com.example.weshare.databean.HomeViewPagerBean;
import com.example.weshare.databean.LocationBean;
import com.example.weshare.databean.ProductBean;
import com.example.weshare.databean.ProductIntroduceInfoBean;
import com.example.weshare.databean.UpdateBean;
import com.example.weshare.databean.UserBean;
import com.example.weshare.databean.UserCommentBean;

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
    @POST("/mobile/control/index/getProInfo.php")
    Call<HomeRecyclerViewBean> getRecyclerviewInfo(@Field("submit") String value, @Field("sid") String sid);//获取中间三个横条前2个的数据
    @FormUrlEncoded
    @POST("/mobile/control/index/special_area_all.php")
    Call<HomeListBean> getListInfo(@Field("submit") String value, @Field("sid") String sid);//获取首页listview的数据
    @FormUrlEncoded
    @POST("/mobile/control/index/special_area.php")
    Call<HomeRecyclerViewLogoBean> getLogoInfo(@Field("submit") String value, @Field("sid") String sid);//获取中间三个横条第3个的数据
    @FormUrlEncoded
    @POST("/mobile/control/index/advs.php")
    Call<HomeViewPagerBean> getViewpagerInfo(@Field("sid") String sid);//获取上方滚动Viewpager的数据
    @FormUrlEncoded
    @POST("/mobile/control/index/new_special_area_info.php")//获取首页listview详情的数据
    Call<HomeListDetailBean> getListDetailInfo(@Field("id") String id,@Field("sid") String sid);

    @FormUrlEncoded
    @POST("mobile/apkdesc.php")//关于享享
    Call<AboutXXBean> aboutXX(@Field("submit")String submit, @Field("sid")String sid);

    @FormUrlEncoded
    @POST("/mobile/control/index/start_advs.php")//获取欢迎页第二个广告的数据
    Call<StartADBean> getSatrtADInfo(@Field("sid") String sid);

    @FormUrlEncoded
    @POST("/mobile/control/product/detail.php")//获取商品详情页商品简介和图文详情的数据
    Call<ProductIntroduceInfoBean> getIntroduceInfo(@Field("id") String id,@Field("sid") String sid);

    @FormUrlEncoded
    @POST("/mobile/control/product/comment.php?show=evaluation")//获取商品详情页用户评价数据
    Call<UserCommentBean> getCommentInfo(@Field("submit") String submit,@Field("sid") String sid,@Field("pid") String pid);

    @FormUrlEncoded
    @POST("mobile/reg_step1.php")//注册第一步,请求验证码
    Call<ResponseBody> getCheckWord(@Field("sid")String sid,@Field("mobile")String mobile);

    @FormUrlEncoded
    @POST("mobile/control/sms/admin_sms.php")//重新发送验证码
    Call<ResponseBody> getCheckWordAgain(@Field("mobile")String mobile,@Field("sid")String sid);

    @FormUrlEncoded
    @POST("mobile/reg_step2.php")//校验验证码
    Call<ResponseBody> verifyCheckword(@Field("sid")String sid,@Field("checkword")String checkword);

    @FormUrlEncoded
    @POST("mobile/reg_step3.php")//设置密码
    Call<ResponseBody> setPWD(@Field("password")String password,@Field("invitecode")String invitecode,@Field("sid")String sid);

    @POST("mobile/control/member/myaccount.php?new=1")//注册成功后自动登录
    Call<ResponseBody> newAccount(@Query("sid")String sid);

    @FormUrlEncoded
    @POST("mobile/location.php?show=get")//获取地址信息列表
    Call<LocationBean> getAdressesData(@Field("provinceid")String provinceid, @Field("sid")String sid, @Field("cityid")String cityid);

    @FormUrlEncoded
    @POST("mobile/login.php")//登录
    Call<UserBean> login(@Field("username")String username, @Field("password")String password, @Field("sid")String sid);

    @FormUrlEncoded
    @POST("mobile/control/member/save_token.php")//保存Token
    Call<ResponseBody> saveToken(@Field("token")String token, @Field("type")int type, @Field("sid")String sid);

    @FormUrlEncoded
    @POST("mobile/control/product/cart.php?show=receive")//获取购物车信息
    Call<ProductBean> getCartInfo(@Field("index")int index, @Field("length")String length, @Field("sid")String sid);

    @FormUrlEncoded
    @POST("mobile/control/index/after_service.php")//获取帮助信息
    Call<HelpBean> getHelp(@Field("submit")String submit, @Field("sid")String sid);
}
