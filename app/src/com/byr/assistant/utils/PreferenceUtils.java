package com.byr.assistant.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import static android.content.Context.MODE_PRIVATE;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;

/**
 * Helpers for dealing with preferences
 * User: orange
 * Date: 13-9-4
 * Time: 下午10:31
 */
public class PreferenceUtils {

    private final static String PREFERENCES_NAME = "code";

    /**
     * Get code browsing preferences
     *
     * @param context
     * @return preferences
     */
    public static SharedPreferences getSharedPreferences(final Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
    }

    private static boolean isEditorApplyAvailable() {
        return SDK_INT > GINGERBREAD;
    }

    /**
     * Save preferences in given editor
     *
     * @param editor
     */
    public static void save(final Editor editor) {
        if (isEditorApplyAvailable())
            editor.apply();
        else
            editor.commit();
    }

    /**
     * Clear all preferences
     *
     * @param context
     */
    public static void clear(final Context context) {
        SharedPreferences sharedPreference = getSharedPreferences(context);
        Editor editor = sharedPreference.edit();
        editor.clear();
        save(editor);
    }

    /**
     * Get int value from preferences, return default value if there is no key.
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return value
     */
    public static int getInt(final Context context, final String key, final int defaultValue) {
        if (key == null || key.equals("")) {
            return defaultValue;
        }
        SharedPreferences sharedPreference = getSharedPreferences(context);
        return sharedPreference.getInt(key, defaultValue);

    }

    /**
     * Get boolean value from preferences, return default value if there is no key.
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return value
     */
    public static boolean getBoolean(final Context context, final String key, final boolean defaultValue) {
        if (key == null || key.equals("")) {
            return defaultValue;
        }
        SharedPreferences sharedPreference = getSharedPreferences(context);
        return sharedPreference.getBoolean(key, defaultValue);
    }

    /**
     * Get string value from preferences, return default value if there is no key.
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return value
     */
    public static String getString(final Context context, final String key, final String defaultValue) {
        if (key == null || key.equals("")) {
            return defaultValue;
        }
        SharedPreferences sharedPreference = getSharedPreferences(context);
        return sharedPreference.getString(key, defaultValue);
    }

    /**
     * Set int value into prefrences
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setInt(final Context context, final String key, final int value) {
        SharedPreferences sharedPreference = getSharedPreferences(context);
        Editor editor = sharedPreference.edit();
        editor.putInt(key, value);
        save(editor);
    }

    /**
     * Set string value into prefrences
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setString(Context context, String key, String value) {
        SharedPreferences sharedPreference = getSharedPreferences(context);
        Editor editor = sharedPreference.edit();
        editor.putString(key, value);
        save(editor);
    }

    /**
     * Set boolean value into prefrences
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreference = getSharedPreferences(context);
        Editor editor = sharedPreference.edit();
        editor.putBoolean(key, value);
        save(editor);
    }


}
