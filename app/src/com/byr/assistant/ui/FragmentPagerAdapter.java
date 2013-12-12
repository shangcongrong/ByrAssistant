package com.byr.assistant.ui;

import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import java.util.List;

/**
 * http://code.google.com/p/android/issues/detail?id=14944.
 * User: orange
 * Date: 13-9-12
 * Time: 下午4:54
 */
public abstract class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter implements FragmentProvider {

    private final SherlockFragmentActivity activity;

    private SherlockFragment selected;


    public FragmentPagerAdapter(SherlockFragmentActivity activity) {
        super(activity.getSupportFragmentManager());

        this.activity = activity;
    }

    @Override
    public SherlockFragment getSelected() {
        return selected;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);

        boolean changed = false;
        if (object instanceof SherlockFragment) {
            changed = selected != object;
            selected = (SherlockFragment) object;
        } else {
            changed = object != null;
            selected = null;
        }

        if (changed)
            activity.invalidateOptionsMenu();

    }
}
