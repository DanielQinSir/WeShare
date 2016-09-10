package com.example.weshare.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-06
 * Time: 10:39
 * For:
 */

public class HttpServiceUtil
{
    public static final String BASE_URL = "http://www.xxiang365.com/";
    public static String SID = "tm5jljtckk3kijrvq2jqetosu2";

    private static HttpService mHttpService;
    public static HttpService init()
    {
        if (mHttpService == null)
        {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            mHttpService = retrofit.create(HttpService.class);
        }
        return mHttpService;
    }

}
