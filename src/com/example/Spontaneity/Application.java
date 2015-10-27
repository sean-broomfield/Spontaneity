package com.example.Spontaneity;

import android.content.Context;
import android.content.SharedPreferences;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by seanbroomfield on 10/19/15.
 */
public class Application extends android.app.Application {

    public static final String INTENT_EXTRA_LOCATION = "location";
    private static final String KEY_SEARCH_DISTANCE = "searchDistance";
    private static final float DEFAULT_SEARCH_DISTANCE = 250.0f;
    private static SharedPreferences preferences;
    //private static ConfigHelper configHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        //ParseObject.registerSubclass(AnywallPost.class);
        Parse.initialize(this, "KHbB4KhUm4AnfG5rDTMorqRlqmu0qh4aFw2XE26V", "vnhZTlGaGSGsGpIJMu8Ksp66MxtFJ6KdoE4wAlkB");
        preferences = getSharedPreferences("com.example.Spontaneity", Context.MODE_PRIVATE);

        //configHelper = new ConfigHelper();
        //configHelper.fetchConfigIfNeeded();
    }

    public static float getSearchDistance() {
        return preferences.getFloat(KEY_SEARCH_DISTANCE, DEFAULT_SEARCH_DISTANCE);
    }

//    public static ConfigHelper getConfigHelper() {
//        return configHelper;
//    }

    public static void setSearchDistance(float value) {
        preferences.edit().putFloat(KEY_SEARCH_DISTANCE, value).apply();
    }
}
