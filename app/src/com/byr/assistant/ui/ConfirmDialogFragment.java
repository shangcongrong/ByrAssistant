package com.byr.assistant.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;


public class ConfirmDialogFragment extends DialogFragmentHelper implements OnClickListener {

    private static final String TAG = "confirm_dialog";

    public static void show(final DialogFragmentActivity activity, final int requestCode, final String title,
                            final String message) {
        show(activity, requestCode, title, message, null);
    }

    public static void show(final DialogFragmentActivity activity, final int requestCode, final String title,
                            final String message, final Bundle bundle) {
        Bundle arguments = createArguments(title, message, requestCode);
        if (bundle != null)
            arguments.putAll(bundle);
        show(activity, new ConfirmDialogFragment(), arguments, TAG);
    }

    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AlertDialog dialog = LightAlertDialog.create(getActivity());
        dialog.setTitle(getTitle());
        dialog.setMessage(getMessage());
        dialog.setButton(BUTTON_POSITIVE, getResources().getString(android.R.string.yes), this);
        dialog.setButton(BUTTON_NEGATIVE, getResources().getString(android.R.string.no), this);
        dialog.setOnCancelListener(this);
        return dialog;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
        switch (which) {
            case BUTTON_POSITIVE:
                onResult(RESULT_OK);
                break;
            case BUTTON_NEGATIVE:
                onResult(RESULT_CANCELED);
                break;
        }
    }


}
