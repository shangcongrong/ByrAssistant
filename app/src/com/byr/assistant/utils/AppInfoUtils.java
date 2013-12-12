package com.byr.assistant.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.byr.assistant.R;

import java.util.Locale;

public class AppInfoUtils {

    private static String TAG = "AppInfoUtils";

    /**
     * Returns the language code for this Locale or the empty string if no language was set.
     */
    public static String getLanguage() {
        String language = null;
        try {
            language = Locale.getDefault().getLanguage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return language;
    }

    /**
     * Returns the country code for this locale, or "" if this locale doesn't correspond to a specific country.
     */
    public String getCountry() {
        String country = null;
        try {
            country = Locale.getDefault().getCountry();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }

    /**
     * Retrieve the information about an application's version code.
     *
     * @param context
     * @return
     */
    public int getVersionCode(Context context) {
        int versionCode = 1;
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(
                    context.getApplicationContext().getPackageName(), 0);
            versionCode = pi.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * Retrieve the information about an application's version name.
     *
     * @param context
     * @return
     */
    public String getVersionName(Context context) {
        String versionName = null;
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(
                    context.getApplicationContext().getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * Retrieve the information about an application's meta-data.
     *
     * @param context
     * @return
     */
    public String getMetaData(Context context, String key) {
        String value = null;
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            value = String.valueOf(ai.metaData.get(key));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return value;
    }

    /**
     * Retrieve the information about an application's name.
     *
     * @param context
     * @return
     */
    public String getAppName(Context context) {
        return context.getResources().getString(R.string.app_name);
    }

}
