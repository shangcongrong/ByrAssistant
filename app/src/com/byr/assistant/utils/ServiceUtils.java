package com.byr.assistant.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import static android.content.Context.WINDOW_SERVICE;
import static android.util.TypedValue.COMPLEX_UNIT_DIP;

/**
 * Helpers for dealing with system services
 * User: orange
 * Date: 13-10-12
 * Time: 上午1:23
 */
public class ServiceUtils {

    /**
     * Get default display
     *
     * @param context
     * @return Display
     */
    public static Display getDisplay(final Context context) {
        return ((WindowManager) context.getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
    }

    /**
     * Get default display
     *
     * @param view
     * @return Display
     */
    public static Display getDisplay(final View view) {
        return getDisplay(view.getContext());
    }

    /**
     * Get default display width
     *
     * @param context
     * @return display
     */
    @SuppressWarnings("deprecation")
    public static int getDisplayWidth(final Context context) {
        return getDisplay(context).getWidth();
    }

    /**
     * Get default display width
     *
     * @param view
     * @return display
     */
    public static int getDisplayWidth(final View view) {
        return getDisplayWidth(view.getContext());
    }

    /**
     * Get pixels from dps
     *
     * @param resources
     * @param dp
     * @return pixels
     */
    public static float getPixels(final Resources resources, final int dp) {
        return TypedValue.applyDimension(COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    /**
     * Get pixels from dps
     *
     * @param view
     * @param dp
     * @return pixels
     */
    public static float getPixels(final View view, final int dp) {
        return getPixels(view.getResources(), dp);
    }

    /**
     * Get int pixels from dps
     *
     * @param resources
     * @param dp
     * @return pixels
     */
    public static int getIntPixels(final Resources resources, final int dp) {
        float pixels = getPixels(resources, dp);
        return (int) Math.floor(pixels + 0.5f);
    }

    /**
     * Get int pixels from dps
     *
     * @param view
     * @param dp
     * @return pixels
     */
    public static int getIntPixels(final View view, final int dp) {
        return getIntPixels(view.getResources(), dp);
    }

}
