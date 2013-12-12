package com.byr.assistant.utils;

import android.app.Activity;
import com.byr.assistant.core.Toaster;

/**
 * Utilities for displaying toast notifications
 * <p/>
 * User: orange
 * Date: 13-10-18
 * Time: 上午2:14
 */
public class ToastUtils {

    public static void show(final Activity activity, final String message) {
        Toaster.showLong(activity, message);
    }

    public static void show(final Activity activity, final int resId) {
        Toaster.showLong(activity, resId);
    }

}
