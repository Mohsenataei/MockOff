package Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class SaveSharedPreference {
    private static final String APITOKEN= "ApiToken";
    private static final String CITY ="city";
    private static final String SHOP_ID = "shop_id";
    private static final String SHOP_NAME = "shop_name";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String POST = "post";
    private static final String SHOP_IDS = "shop_ids";
    private static final String POST_IDS = "post_ids";

    private Followed followedShops;

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


    // saving ids of followed shops for later use
    public static Followed getFollowedShops(Context context) {
         Gson gson = new Gson();
         String json = getSharedPreferences(context).getString(SHOP_IDS,"");
         return gson.fromJson(json, Followed.class);
    }

    public static void setFollowedShops(Context context, Followed followedShops) {

        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(followedShops);
        editor.putString(SHOP_IDS,json);
        editor.apply();
    }



    // saving ids of bookmarked posts for later use
    public static Followed getBookmarked(Context context) {
        Gson gson = new Gson();
        String json = getSharedPreferences(context).getString(POST_IDS,"");
        return gson.fromJson(json, Followed.class);
    }

    public static void setBookmarked(Context context, Followed bookmarked) {

        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(bookmarked);
        editor.putString(POST_IDS,json);
        editor.apply();
    }
}

// 09187047846
