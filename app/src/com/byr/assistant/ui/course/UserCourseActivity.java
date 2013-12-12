package com.byr.assistant.ui.course;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.byr.assistant.R;
import com.byr.assistant.core.sync.CourseUtils;
import com.byr.assistant.core.sync.RestClient;
import com.byr.assistant.core.model.Course;
import com.byr.assistant.ui.TitleActivity;
import com.byr.assistant.ui.home.HomeActivity;
import com.byr.assistant.utils.ActivityUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import org.json.JSONException;


import java.util.List;

/**
 * User: orange
 * Date: 13-11-19
 * Time: 下午2:36
 */
public class UserCourseActivity extends TitleActivity<UserCoursePagerAdapter> {

    private static String TAG = "com.byr.assistant.ui.course.UserCourseActivity";

    protected UserCoursePagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureActionBar();

    }

    private void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.dropdown_course);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.navigation_course);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu optionsMenu) {
        getSupportMenuInflater().inflate(R.menu.menu_add, optionsMenu);
        return super.onCreateOptionsMenu(optionsMenu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                ActivityUtils.goHome(this, HomeActivity.class);
                return true;
            case R.id.add:
                Intent intent = new Intent(this, AddCourseActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected UserCoursePagerAdapter createAdapter() {
        adapter = new UserCoursePagerAdapter(this, false);
        return adapter;
    }


    @Override
    public void onCenterItemClick(int position) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
