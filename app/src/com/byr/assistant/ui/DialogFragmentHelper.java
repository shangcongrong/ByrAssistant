package com.byr.assistant.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import roboguice.fragment.RoboDialogFragment;

import static android.app.Activity.RESULT_CANCELED;


public abstract class DialogFragmentHelper extends RoboDialogFragment implements OnClickListener {

    private static final String ARG_TITLE = "title";

    private static final String ARG_MESSAGE = "message";

    private static final String ARG_REQUEST_CODE = "requestCode";

    /**
     * Show dialog
     *
     * @param activity
     * @param fragment
     * @param arguments
     * @param tag
     */
    protected static void show(DialogFragmentActivity activity, DialogFragmentHelper fragment, Bundle arguments, String tag) {
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment current = manager.findFragmentByTag(tag);
        if (current != null)
            transaction.remove(current);
        transaction.addToBackStack(null);

        fragment.setArguments(arguments);
        fragment.show(manager, tag);
    }

    protected static Bundle createArguments(final String title, final String message, final int requestCode) {
        Bundle arguments = new Bundle();
        arguments.putString(ARG_TITLE, title);
        arguments.putString(ARG_MESSAGE, message);
        arguments.putInt(ARG_REQUEST_CODE, requestCode);
        return arguments;

    }

    protected void onResult(final int resultCode) {
        final DialogFragmentActivity activity = (DialogFragmentActivity) getActivity();
        if (activity != null) {
            final Bundle arguments = getArguments();
            if (arguments != null) {
                activity.onDialogResult(arguments.getInt(ARG_REQUEST_CODE), resultCode, arguments);
            }
        }
    }

    protected String getTitle() {
        return getArguments().getString(ARG_TITLE);
    }

    protected String getMessage() {
        return getArguments().getString(ARG_MESSAGE);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        onResult(RESULT_CANCELED);
    }

    protected AlertDialog createAlertDialog() {
        final AlertDialog alertDialog = LightAlertDialog.create(getActivity());
        alertDialog.setTitle(getTitle());
        alertDialog.setMessage(getMessage());
        alertDialog.setCancelable(true);
        alertDialog.setOnCancelListener(this);
        return alertDialog;
    }
}

