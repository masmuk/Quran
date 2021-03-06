package com.MohamedTaha.Imagine.Quran.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPerefrenceHelper {
    private static final String SHARED_PREFRENCES_NAME = "save_path_images";
    private static final String SHARED_PREFRENCES_NAME_AZKAR = "save_path_azkar";
    private static final String SHARED_PREFRENCES_WAY_USING = "way_using";
    private static SharedPreferences getSharedPrefrencesForWayUsing(Context context){
        return context.getSharedPreferences(SHARED_PREFRENCES_WAY_USING,Context.MODE_PRIVATE);
    }
    private static SharedPreferences getSharedPrefrences(Context context){
        return context.getSharedPreferences(SHARED_PREFRENCES_NAME,Context.MODE_PRIVATE);
    }
    private static SharedPreferences getSharedPrefrencesForAzkar(Context context){
        return context.getSharedPreferences(SHARED_PREFRENCES_NAME_AZKAR,Context.MODE_PRIVATE);
    }
    public static void putBooleanForAzkar(Context context,String key, boolean value){
        getSharedPrefrencesForAzkar(context).edit().putBoolean(key,value).apply();
    }
    public static void putIntForAzkar(Context context,String key, int value){
        getSharedPrefrencesForAzkar(context).edit().putInt(key,value).apply();
    }
    public static int getIntForAzkar(Context context , String key, int defaultValue){
        return getSharedPrefrencesForAzkar(context).getInt(key,defaultValue);
    }
    public static boolean getBooleanForAzkar(Context context,String key, boolean defaultValue){
        return getSharedPrefrencesForAzkar(context).getBoolean(key,defaultValue);
    }
    public static boolean getBooleanForWayUsing(Context context,String key, boolean defaultValue){
        return getSharedPrefrencesForWayUsing(context).getBoolean(key,defaultValue);
    }
    public static void putBooleanForWayUsing(Context context,String key, boolean value){
        getSharedPrefrencesForWayUsing(context).edit().putBoolean(key,value).apply();
    }
    public static void removeDataForWayUsing(Context context){
        getSharedPrefrencesForWayUsing(context).edit().clear().commit();
    }
    public static void removeDataForAzkar(Context context){
        getSharedPrefrencesForAzkar(context).edit().clear().commit();
    }
    public static void putInt(Context context,String key, int value){
        getSharedPrefrences(context).edit().putInt(key,value).commit();
    }
    public static void putBoolean(Context context,String key, boolean value){
        getSharedPrefrences(context).edit().putBoolean(key,value).commit();
    }
    public static int getInt(Context context , String key, int defaultValue){
         return getSharedPrefrences(context).getInt(key,defaultValue);
    }
    public static boolean getBoolean(Context context,String key, boolean defaultValue){
       return getSharedPrefrences(context).getBoolean(key,defaultValue);
    }
    public static void removeData(Context context){
        getSharedPrefrences(context).edit().clear().commit();
    }
}
