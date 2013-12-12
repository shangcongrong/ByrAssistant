package com.byr.assistant.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.byr.assistant.core.ViewFinder;
import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-9-12
 * Time: 上午10:23
 * To change this template use File | Settings | File Templates.
 */
public abstract class DialogFragment extends RoboSherlockFragment implements DialogResultListener {

    protected ViewFinder finder;

    protected boolean isUsable() {
        return getActivity() != null;
    }

    @Override
    public void onDialogResult(int requestCode, int resultCode, Bundle arguments) {

    }

    protected <V extends Serializable> V getSerializableExtra(final String name) {
        Activity activity = getActivity();
        if (activity != null) {
            return (V) activity.getIntent().getSerializableExtra(name);
        } else
            return null;
    }

    protected String getStringExtra(final String name) {
        Activity activity = getActivity();
        if (activity != null) {
            return activity.getIntent().getStringExtra(name);

        } else {
            return null;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        finder = new ViewFinder(view);
    }
}
