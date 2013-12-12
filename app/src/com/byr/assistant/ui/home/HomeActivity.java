package com.byr.assistant.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.byr.assistant.R;
import com.byr.assistant.core.model.Course;
import com.byr.assistant.core.model.Event;
import com.byr.assistant.core.model.Forum;
import com.byr.assistant.core.model.Note;
import com.byr.assistant.core.persistance.DbCache;
import com.byr.assistant.core.persistance.DbManager;
import com.byr.assistant.core.persistance.PersistableCourse;
import com.byr.assistant.core.persistance.PersistableNote;
import com.byr.assistant.core.sync.CourseUtils;
import com.byr.assistant.core.sync.RestClient;
import com.byr.assistant.ui.DialogFragmentActivity;
import com.byr.assistant.ui.event.EventActivity;
import com.byr.assistant.ui.forum.ForumActivity;
import com.byr.assistant.ui.note.NoteActivity;
import com.byr.assistant.ui.course.UserCourseActivity;
import com.byr.assistant.utils.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.actionbarsherlock.app.ActionBar.NAVIGATION_MODE_LIST;
import static com.byr.assistant.ui.home.HomeDropDownListAdapter.*;

/**
 * User: orange
 * Date: 13-11-19
 * Time: 下午2:36
 */
public class HomeActivity extends DialogFragmentActivity implements OnNavigationListener {

    private static String TAG = "com.byr.assistant.ui.home.HomeActivity";

    private HomeDropDownListAdapter homeAdapter;

    private DbCache cache;

    private HomeCellCourse cellCourseFragment;

    private HomeCellForum cellForumFragment;

    private HomeCellEvent cellEventFragment;

    private HomeCellNote cellNoteFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureActionBar();
        setContentView(R.layout.home_activity);
        cellCourseFragment = (HomeCellCourse) getSupportFragmentManager().findFragmentById(R.id.cell_course_fragment);
        cellEventFragment = (HomeCellEvent) getSupportFragmentManager().findFragmentById(R.id.cell_event_fragment);
        cellForumFragment = (HomeCellForum) getSupportFragmentManager().findFragmentById(R.id.cell_forum_fragment);
        cellNoteFragment = (HomeCellNote) getSupportFragmentManager().findFragmentById(R.id.cell_note_fragment);
        cache = DbManager.getInstance(this).getDatabaseCache();
        try {
            request();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setSelectedNavigationItem(ACTION_HOME);
        List<Note> notes = cache.loadFromDB(new PersistableNote());
        if (notes == null)
            cellNoteFragment.loadData(null);
        else
            cellNoteFragment.loadData(notes.get(0));

        List<Course> courses = cache.loadFromDB(new PersistableCourse(), "workday=?", new String[]{String.valueOf(TimeUtils.getWorkday())}, null, null, null);
        if (courses == null)
            cellCourseFragment.loadData(null);
        else
            cellCourseFragment.loadData(courses.get(0));
    }

    private void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(NAVIGATION_MODE_LIST);
        actionBar.setSelectedNavigationItem(0);

        homeAdapter = new HomeDropDownListAdapter(this, createNavigationList());
        actionBar.setListNavigationCallbacks(homeAdapter, this);
    }

    private List<NavigationListItem> createNavigationList() {
        List<NavigationListItem> list = new ArrayList<NavigationListItem>();
        NavigationListItem item;
        item = new NavigationListItem(R.string.navigation_dashboard, R.drawable.dropdown_dashboard);
        list.add(0, item);
        item = new NavigationListItem(R.string.navigation_course, R.drawable.dropdown_course);
        list.add(1, item);
        item = new NavigationListItem(R.string.navigation_event, R.drawable.dropdown_event);
        list.add(2, item);
        item = new NavigationListItem(R.string.navigation_forum, R.drawable.dropdown_forum);
        list.add(3, item);
        item = new NavigationListItem(R.string.navigation_note, R.drawable.dropdown_note);
        list.add(4, item);
        return list;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        switch (homeAdapter.getAction(itemPosition)) {
            case ACTION_HOME:
                break;
            case ACTION_COURSE:
                startActivity(new Intent(this, UserCourseActivity.class));
                break;
            case ACTION_EVNET:
                startActivity(new Intent(this, EventActivity.class));
                break;
            case ACTION_FORUM:
                startActivity(new Intent(this, ForumActivity.class));
                break;
            case ACTION_NOTE:
                startActivity(new Intent(this, NoteActivity.class));
                break;
        }
        return true;
    }

    private void request() throws JSONException {

        AjaxParams params = new AjaxParams();
        params.put("message", "mainpage");

        RestClient.get("MainServlet", params, new AjaxCallBack<String>() {

            @Override
            public void onSuccess(String result) {

                JsonParser parser = new JsonParser();
                JsonObject object = parser.parse(result).getAsJsonObject();
                String status = object.get("status").getAsString();

                if (status.equals("OK")) {
                    loadDate(object.get("strResponse").getAsJsonObject());
                } else {

                }

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void loadDate(JsonObject object) {
        Gson gson = null;

        gson = new Gson();
        Event event = gson.fromJson(object.get("todayevents").getAsJsonObject(), Event.class);
        cellEventFragment.loadData(event);

        gson = new Gson();
        Forum forum = gson.fromJson(object.get("toptentop").getAsJsonObject(), Forum.class);
        cellForumFragment.loadData(forum);

        List<Note> notes = cache.loadFromDB(new PersistableNote());
        if (notes == null)
            cellNoteFragment.loadData(null);
        else
            cellNoteFragment.loadData(notes.get(0));

        List<Course> courses = cache.loadFromDB(new PersistableCourse(), "workday=?", new String[]{String.valueOf(TimeUtils.getWorkday())}, null, null, null);
        if (courses == null)
            cellCourseFragment.loadData(null);
        else
            cellCourseFragment.loadData(courses.get(0));

//        gson = new Gson();
//        Course course = gson.fromJson(object.get("todaycourses").getAsJsonObject(), Course.class);
//        gson = new Gson();
//        Note note = gson.fromJson(object.get("todaynotes").getAsJsonObject(), Note.class);
    }

}
