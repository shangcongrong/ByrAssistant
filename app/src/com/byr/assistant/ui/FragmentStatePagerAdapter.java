package com.byr.assistant.ui;

import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

/**
 * User: orange
 * Date: 13-9-12
 * Time: 下午5:07
 */
public abstract class FragmentStatePagerAdapter extends android.support.v4.app.FragmentStatePagerAdapter implements FragmentProvider {

    private SherlockFragmentActivity activity;

    private SherlockFragment selected;

    public FragmentStatePagerAdapter(SherlockFragmentActivity activity) {
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
            changed = object != selected;
            selected = (SherlockFragment) object;
        } else {
            changed = object != null;
            selected = null;
        }

        if (changed)
            activity.invalidateOptionsMenu();
    }
}
