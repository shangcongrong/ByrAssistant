package com.byr.assistant.ui.course;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.byr.assistant.ui.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-27
 * Time: 下午9:30
 * To change this template use File | Settings | File Templates.
 */
public class AddCoursePagerAdapter extends FragmentPagerAdapter {
    private final FragmentManager fragmentManager;

    private Resources resources;

    private boolean isCourseEmpty;

    private ArrayList<UserCourseListFragment> list;


    public AddCoursePagerAdapter(final SherlockFragmentActivity activity, boolean isCourseEmpty) {
        super(activity);

        fragmentManager = activity.getSupportFragmentManager();
        resources = activity.getResources();
        this.isCourseEmpty = isCourseEmpty;

    }

    @Override
    public Fragment getItem(int position) {
        return new AddCourseListFragment();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 1;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        return fragment;
    }

}
