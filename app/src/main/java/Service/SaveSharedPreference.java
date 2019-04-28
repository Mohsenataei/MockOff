package Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    static final String APITOKEN= "ApiToken";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setAPITOKEN(Context ctx, String apitoken)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(APITOKEN, apitoken);
        editor.commit();
    }

    public static String getAPITOKEN(Context ctx)
    {
        return getSharedPreferences(ctx).getString(APITOKEN, "");
    }
}
