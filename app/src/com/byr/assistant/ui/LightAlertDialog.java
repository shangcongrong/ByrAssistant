package com.byr.assistant.ui;

import android.app.AlertDialog;
import android.content.Context;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;


public class LightAlertDialog extends AlertDialog {

    public static AlertDialog create(final Context context) {
        if (SDK_INT > ICE_CREAM_SANDWICH) {
            return new LightAlertDialog(context, THEME_HOLO_LIGHT);
        } else {
            return new LightAlertDialog(context);
        }
    }

    private LightAlertDialog(Context context) {
        super(context);
    }

    private LightAlertDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder extends AlertDialog.Builder {

        public static Builder create(final Context context) {
            if (SDK_INT > ICE_CREAM_SANDWICH) {
                return new Builder(context, THEME_HOLO_LIGHT);
            } else {
                return new Builder(context);
            }
        }

        public Builder(Context context) {
            super(context);
        }

        public Builder(Context context, int theme) {
            super(context, theme);
        }
    }
}
