package com.byr.assistant.ui.course;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.byr.assistant.R;
import com.byr.assistant.core.SingleTypeAdapter;
import com.byr.assistant.core.model.Course;
import com.byr.assistant.core.persistance.DbCache;
import com.byr.assistant.core.persistance.DbManager;
import com.byr.assistant.core.persistance.PersistableUserCourse;
import com.byr.assistant.ui.ItemListFragment;

import java.util.List;

/**
 * User: orange
 * Date: 13-11-19
 * Time: 上午12:20
 */
public class UserCourseListFragment extends ItemListFragment<Course> {

    private String TAG = "com.byr.assistant.ui.course.UserCourseListFragment";

    private DbCache cache;

    private int workday;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        cache = DbManager.getInstance(activity).getDatabaseCache();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(TAG, workday);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null)
            workday = bundle.getInt("workday", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.course_list_fragment, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null)
            workday = savedInstanceState.getInt(TAG, 0);

    }

    @Override
    public void onResume() {
        super.onResume();
        List<Course> list = cache.loadFromDB(new PersistableUserCourse(), "workday=?", new String[]{String.valueOf(workday)}, null, null, "startTime desc");
        if (list != null)
            onLoadFinished(list);
    }

    @Override
    public SingleTypeAdapter<Course> createAdapter(List<Course> items) {
        return new UserCourseAdapter(getActivity(), items);
    }

    @Override
    public void onLoadFinished(List items) {
        super.onLoadFinished(items);
    }
}
