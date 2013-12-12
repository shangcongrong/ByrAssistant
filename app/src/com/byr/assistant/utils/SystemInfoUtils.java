package com.byr.assistant.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;


public class SystemInfoUtils {

    private static String TAG = "SystemInfoUtils";

    /**
     * Get size of window
     *
     * @param context
     * @return size
     */
    public static Point getDisplay(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        return new Point(dm.widthPixels, dm.heightPixels);
    }

}
