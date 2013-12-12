package com.byr.assistant.utils;

import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.text.format.DateUtils.*;

/**
 * Utilities for dealing with times and dates
 * <p/>
 * User: orange
 * Date: 13-10-18
 * Time: 上午2:13
 */
public class TimeUtils {

    public static CharSequence getRelativeTime(final Date date) {
        long now = System.currentTimeMillis();
        if (Math.abs(now - date.getTime()) > 60000)
            return DateUtils.getRelativeTimeSpanString(date.getTime(), now, MINUTE_IN_MILLIS,
                    FORMAT_SHOW_DATE | FORMAT_SHOW_YEAR | FORMAT_NUMERIC_DATE);
        else
            return "just now";
    }

    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat timeFormater = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String str = timeFormater.format(date);
        return str;
    }

    public static int getWorkday() {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String workday = dateFm.format(date);
        if (workday.equals("星期一"))
            return 1;
        if (workday.equals("星期二"))
            return 2;
        if (workday.equals("星期三"))
            return 3;
        if (workday.equals("星期四"))
            return 4;
        if (workday.equals("星期五"))
            return 5;
        if (workday.equals("星期六"))
            return 6;
        if (workday.equals("星期日"))
            return 7;
        return 0;

    }

}
