package com.byr.assistant.utils;

import android.app.Activity;
import android.content.Intent;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

/**
 * Utilities for an {@link android.app.Activity}
 * <p/>
 * User: orange
 * Date: 13-10-22
 * Time: 下午1:58
 */
public class ActivityUtils {

    /**
     * Finish the given activity and start a home activity class.
     * <p/>
     * This mirror the behavior of the home action bar button that clears the
     * current activity and starts or brings another activity to the top.
     *
     * @param activity
     * @param homeActivityClass
     */
    public static void goHome(Activity activity, Class<?> homeActivityClass) {
        activity.finish();
        Intent intent = new Intent(activity, homeActivityClass);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

}
