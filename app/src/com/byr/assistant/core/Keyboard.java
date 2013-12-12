package com.byr.assistant.core;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Keyboard Utilities
 * <p/>
 * User: orange
 * Date: 13-10-23
 * Time: 上午1:23
 */
public class Keyboard {

    /**
     * Hide soft input manager
     *
     * @param view
     * @return
     */
    public static View hideSoftInput(final View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext().getSystemService(INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return view;
    }

}
