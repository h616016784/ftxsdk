package com.ftxad.ftxsdk.net;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ftxad.ftxsdk.bean.response.RspAdvert;
import com.ftxad.ftxsdk.bean.response.RspClick;
import com.ftxad.ftxsdk.bean.response.RspExposure;
import com.ftxad.ftxsdk.interf.FtxCallback;
import com.ftxad.ftxsdk.net.interceptor.LogInterceptor;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by puyafeng on 2017/9/12.
 */
public class API implements APIConstants {
    private Context mContext;
    private static API sInstance;
    private static APIService apiService;
    private API(Context context) {
        mContext=context;
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(DOMAIN)
                .build();
        apiService = retrofit.create(APIService.class);
    }

    public static API getInstance(Context context) {
        if (sInstance == null) {
            synchronized (API.class) {
                if (sInstance == null) {
                    sInstance = new API(context);
                }
            }
        }
        return sInstance;
    }

    /**
     * 获取广告信息接口
     * @param type
     * @param callback
     */
    public void fantasyWfjs(String type, final FtxCallback<RspAdvert.DataBean> callback){
        Map<String,String> map=new HashMap<>();
        map.put("device","2");
        map.put("type",type);
        map.put("mId","zimuzu");

        Call<RspAdvert> call=apiService.fantasyWfjs(map);
        call.enqueue(new Callback<RspAdvert>() {
            @Override
            public void onResponse(Call<RspAdvert> call, Response<RspAdvert> response) {
                try {
                    RspAdvert rspAdvert=response.body();
                    if ("100000".equals(rspAdvert.getStatus())){
                        if (callback!=null)
                            callback.ftxCallback(rspAdvert.getData());
                    }
                }catch (Exception e){
                    Log.e("fantasyWfjs","onResponse");
                    if (callback!=null)
                        callback.ftxCallback(null);
                    e.printStackTrace();
                    Toast.makeText(mContext,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RspAdvert> call, Throwable t) {
                Log.e("fantasyWfjs","onFailure");
                callback.ftxFailed(call.toString());
                Toast.makeText(mContext,call.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 广告曝光接口
     * @param bean
     */
    public void  addJsENum(RspAdvert.DataBean bean){
        Map<String,String> map=new HashMap<>();
        map.put("orderId",bean.getOrderId());
        map.put("stuffId",bean.getAdStuff().getId()+"");
        map.put("device","2");
        map.put("type","2");
        map.put("adPriceSortId",bean.getAdPriceSortId());
        map.put("mId","zimuzu");
        Call<RspExposure> call=apiService.addJsENum(map);
        call.enqueue(new Callback<RspExposure>() {
            @Override
            public void onResponse(Call<RspExposure> call, Response<RspExposure> response) {

            }

            @Override
            public void onFailure(Call<RspExposure> call, Throwable t) {

            }
        });
    }

    /**
     * 广告点击接口
     * @param bean
     */
    public void addJsCNum(RspAdvert.DataBean bean){
        Map<String,String> map=new HashMap<>();
        map.put("orderId",bean.getOrderId());
        map.put("stuffId",bean.getAdStuff().getId()+"");
        map.put("device","2");
        map.put("type","2");
        map.put("adPriceSortId",bean.getAdPriceSortId());
        map.put("mId","zimuzu");
        Call<RspClick> call=apiService.addJsCNum(map);
        call.enqueue(new Callback<RspClick>() {
            @Override
            public void onResponse(Call<RspClick> call, Response<RspClick> response) {

            }

            @Override
            public void onFailure(Call<RspClick> call, Throwable t) {

            }
        });
    }
}
