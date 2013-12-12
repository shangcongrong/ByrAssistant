package com.byr.assistant.ui.note;

import android.content.Intent;
import android.os.Bundle;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.byr.assistant.R;
import com.byr.assistant.ui.DialogFragmentActivity;
import com.byr.assistant.ui.home.HomeActivity;
import com.byr.assistant.utils.ActivityUtils;

import static com.byr.assistant.Constants.REQUEST_ADD_NOTE;

/**
 * User: orange
 * Date: 13-11-21
 * Time: 下午5:08
 */
public class NoteActivity extends DialogFragmentActivity {

    private static String TAG = "com.byr.assistant.ui.note.NoteActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity);
        configureActionBar();

    }

    private void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.dropdown_note);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.navigation_note);
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
                Intent intent = new Intent(this, AddNoteActivity.class);
                startActivityForResult(intent, REQUEST_ADD_NOTE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
