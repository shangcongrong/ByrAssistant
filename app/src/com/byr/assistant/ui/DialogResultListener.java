package com.byr.assistant.ui;

import android.os.Bundle;


public interface DialogResultListener {

    /**
     * Callback for a dialog finishing and delivering a result
     *
     * @param requestCode
     * @param resultCode
     *            result such as {@link android.app.Activity#RESULT_CANCELED} or
     *            {@link android.app.Activity#RESULT_OK}
     * @param arguments
     */
    void onDialogResult(int requestCode, int resultCode, Bundle arguments);
}
