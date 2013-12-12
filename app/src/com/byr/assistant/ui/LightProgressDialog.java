package com.byr.assistant.ui;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * User: orange
 * Date: 13-9-12
 * Time: 下午5:15
 */
public class LightProgressDialog extends ProgressDialog{


    public LightProgressDialog(Context context) {
        super(context);
    }

    public LightProgressDialog(Context context, int theme) {
        super(context, theme);
    }
}
