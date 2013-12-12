package com.byr.assistant.ui.note;

import android.os.Bundle;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.byr.assistant.R;
import com.byr.assistant.ui.DialogFragmentActivity;
import com.byr.assistant.ui.course.AddCourseListFragment;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-28
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */

public class AddNoteActivity extends DialogFragmentActivity {

    private String TAG = "com.byr.assistant.ui.note.AddNoteActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.note_add_activity);
        configureActionBar();
    }

    private void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.dropdown_note);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.navigation_note);
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
