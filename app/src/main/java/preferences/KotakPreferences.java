package preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class KotakPreferences {

    private static final String PREFERENCE_NAME = "Kotak_pref";
    private SharedPreferences preferences;
    private static KotakPreferences instance;

    public KotakPreferences(Context context) {

        preferences = context.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
    }

    public static KotakPreferences getInstance(Context context) {
        if (instance == null) {
            instance = new KotakPreferences(context);
            return instance;
        }
        return instance;
    }

    public static boolean readBoolean(Context context, String key, boolean defaultValue) {
        if (context != null)
            return getSharedPreference(context).getBoolean(key, defaultValue);
        else return defaultValue;
    }

    public boolean putdata(String key, boolean value) {
        return preferences.edit().putBoolean(key, value).commit();
    }

    private static android.content.SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);


    }
}
