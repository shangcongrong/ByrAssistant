package com.byr.assistant.ui.course;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.byr.assistant.R;
import com.byr.assistant.ui.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * User: orange
 * Date: 13-11-19
 * Time: 下午2:37
 */
public class UserCoursePagerAdapter extends FragmentStatePagerAdapter {

//    private final FragmentManager fragmentManager;

    private static int NUM_OF_WEEKDAYS = 5;

    private static int[] WEEKDAYS = {R.string.title_monday, R.string.title_tuesday, R.string.title_wednesday, R.string.title_thursday, R.string.title_friday};

    private Resources resources;

    private boolean isCourseEmpty;

    private ArrayList<UserCourseListFragment> list;


    public UserCoursePagerAdapter(final SherlockFragmentActivity activity, boolean isCourseEmpty) {
        super(activity);

//        fragmentManager = activity.getSupportFragmentManager();
        resources = activity.getResources();
        this.isCourseEmpty = isCourseEmpty;

    }

    public ArrayList<UserCourseListFragment> getArrayList() {
        return list;
    }

    @Override
    public Fragment getItem(int position) {
        UserCourseListFragment fragment = new UserCourseListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("workday", position + 1);
        fragment.setArguments(bundle);
        return fragment;
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
        return resources.getString(WEEKDAYS[position]);
    }

    @Override
    public int getCount() {
        return NUM_OF_WEEKDAYS;
    }
}
