package com.byr.assistant.ui.course;

import android.os.Bundle;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.byr.assistant.R;
import com.byr.assistant.ui.DialogFragmentActivity;

/**
 * Created by orange on 13-11-26.
 */
public class AddCourseActivity extends DialogFragmentActivity {

    //拓展成多个学院，所以直接用Adapter这种形式

    private String TAG = "com.byr.assistant.ui.course.AddCourseActivity";

    private AddCourseListFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.course_add_activity);
//        fragment = (AddCourseListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        configureActionBar();
    }

    private void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.dropdown_course);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.navigation_course);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
