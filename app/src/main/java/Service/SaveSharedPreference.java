package Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import entities.Post;

public class SaveSharedPreference {
    private static final String APITOKEN= "ApiToken";
    private static final String CITY ="city";
    private static final String SHOP_ID = "shop_id";
    private static final String SHOP_NAME = "shop_name";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String POST = "post";

    private static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setAPITOKEN(Context ctx, String apitoken)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(APITOKEN, apitoken);
        editor.apply();
    }

    public static void setCity(Context ctx, String city){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(CITY,city);
        editor.apply();
    }

    public static String getAPITOKEN(Context ctx)
    {
        return getSharedPreferences(ctx).getString(APITOKEN, "");
    }

    public static String getCity(Context ctx){
        return getSharedPreferences(ctx).getString(CITY,"");
    }
    public static void removeAPITOKEN(Context ctx){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.apply();
    }
    public static void setShopId(Context context, String id){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(SHOP_ID,id);
        editor.apply();
    }

    public static void setShopName(Context context, String name){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(SHOP_NAME,name);
        editor.apply();
    }
    public static void setLatitude(Context context, String lat){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LATITUDE,lat);
        editor.apply();
    }
    public static void setLongitude(Context context, String lon) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LONGITUDE, lon);
        editor.apply();
    }


    public static String getShopId(Context context){
        return getSharedPreferences(context).getString(SHOP_ID,"");
    }
    public static String getShopName(Context context){
        return getSharedPreferences(context).getString(SHOP_NAME,"");
    }
    public static String getLatitude(Context context){
        return getSharedPreferences(context).getString(LATITUDE,"");
    }
    public static String getLongitude(Context context){
        return getSharedPreferences(context).getString(LONGITUDE,"");
    }

//    public static void setPost (Context context, Post post){
//        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
//        editor.put
//    }

}

// 09187047846
