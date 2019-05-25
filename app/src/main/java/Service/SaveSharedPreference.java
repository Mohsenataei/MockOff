package Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    private static final String APITOKEN= "ApiToken";
    private static final String CITY ="city";

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
}

// 09187047846
