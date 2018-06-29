package com.ftxad.ftxsdk.utils;

import android.content.Context;
import android.util.DisplayMetrics;


public class ScreenUtil {

    public static int screenWidth;
    public static int screenHeight;

    public static float density;
    public static float scaleDensity;

    public static int dip2px(float dipValue) {
        return (int) (dipValue * density + 0.5f);
    }

    public static int px2dip(float pxValue) {
        return (int) (pxValue / density + 0.5f);
    }

    public static int sp2px(float spValue) {
        return (int) (spValue * scaleDensity + 0.5f);
    }

    public static int getScreenWidth(Context context){
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        return screenWidth;
    }
    public static int getScreenHeight(Context context){
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenHeight = dm.heightPixels;
        return screenHeight;
    }
}
