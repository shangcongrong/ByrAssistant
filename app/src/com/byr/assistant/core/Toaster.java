package com.byr.assistant.core;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.text.MessageFormat;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * Helper to show {@link android.widget.Toast} notifications
 * <p/>
 * User: orange
 * Date: 13-10-22
 * Time: 上午1:01
 */
public class Toaster {

    public static void show(final Activity activity, final int resId, final int duration) {
        if (activity == null)
            return;

        final Context context = activity.getApplication();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, resId, duration).show();
            }
        });
    }

    public static void show(final Activity activity, final String message, final int duration) {
        if (activity == null)
            return;

        if (TextUtils.isEmpty(message))
            return;

        final Context context = activity.getApplication();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, duration).show();
            }
        });
    }

    public static void showShort(final Activity activity, final int resId) {
        show(activity, resId, LENGTH_SHORT);
    }

    public static void showLong(final Activity activity, final int resId) {
        show(activity, resId, LENGTH_LONG);
    }

    public static void showShort(final Activity activity, final String message) {
        show(activity, message, LENGTH_SHORT);
    }

    public static void showLong(final Activity activity, final String message) {
        show(activity, message, LENGTH_LONG);
    }

    public static void showShort(final Activity activity, final String message, final Object... args) {
        String formmated = MessageFormat.format(message, args);
        show(activity, formmated, LENGTH_SHORT);
    }

    public static void showLong(final Activity activity, final String message, final Object... args) {
        String formmated = MessageFormat.format(message, args);
        show(activity, formmated, LENGTH_LONG);
    }

    public static void showShort(final Activity activity, int resId, final Object... args) {
        if (activity == null)
            return;
        String message = activity.getString(resId);
        showShort(activity, message, args);
    }

    public static void showLong(final Activity activity, int resId, final Object... args) {
        if (activity == null)
            return;
        String message = activity.getString(resId);
        showLong(activity, message, args);
    }

}
