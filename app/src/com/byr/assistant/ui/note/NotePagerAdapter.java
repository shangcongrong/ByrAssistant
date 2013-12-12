package com.byr.assistant.ui.note;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.byr.assistant.R;
import com.byr.assistant.ui.FragmentStatePagerAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-28
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
public class NotePagerAdapter extends FragmentStatePagerAdapter {

    public Resources resources;

    private int[] TITLES = new int[]{
            R.string.title_reminder, R.string.title_note};

    public NotePagerAdapter(SherlockFragmentActivity activity) {
        super(activity);
        resources = activity.getResources();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new NoteListFragment();
            case 0:
                return new ReminderListFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        return fragment;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return resources.getString(TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
