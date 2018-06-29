package com.ftxad.ftxsdk.net;


import com.ftxad.ftxsdk.bean.response.RspAdvert;
import com.ftxad.ftxsdk.bean.response.RspClick;
import com.ftxad.ftxsdk.bean.response.RspExposure;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2018/4/27.
 */

public interface APIService {

    @POST("ad/fantasyWfjs")
    Call<RspAdvert> fantasyWfjs(@QueryMap Map<String, String> map);

    @POST("adClick/addJsENum")
    Call<RspExposure> addJsENum(@QueryMap Map<String, String> map);

    @POST("adClick/addJsCNum")
    Call<RspClick> addJsCNum(@QueryMap Map<String, String> map);
}
