package com.tuzixiansheng.pda.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xx on 2017/6/26.
 */

public class SpUtil {

    public static final String CONFIG = "config";
    private SharedPreferences sSp;
    private static SharedPreferences sSSp;

    //保存boolean
    public static void saveBoolean(Context context, String key, boolean value) {
        if (sSSp == null)
        sSSp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        sSSp.edit().putBoolean(key, value).commit();
    }
    //获取boolean
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sSSp == null)
            sSSp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        return sSSp.getBoolean(key, defValue);
    }

    //保存String
    public static void saveString(Context context, String key, String value) {
        if (sSSp == null)
            sSSp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        sSSp.edit().putString(key, value).commit();
    }
    //获取String
    public static String getString(Context context, String key, String defValue) {
        if (sSSp == null)
            sSSp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        return sSSp.getString(key, defValue);
    }

    //保存String
    public static void saveInt(Context context, String key, int value) {
        if (sSSp == null)
            sSSp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        sSSp.edit().putInt(key, value).commit();
    }
    //获取String
    public static int getInt(Context context, String key, int defValue) {
        if (sSSp == null)
            sSSp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        return sSSp.getInt(key, defValue);
    }
}
