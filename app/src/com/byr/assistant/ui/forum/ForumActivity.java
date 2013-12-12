package com.byr.assistant.ui.forum;

import android.os.Bundle;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.byr.assistant.R;
import com.byr.assistant.ui.DialogFragmentActivity;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-29
 * Time: 下午7:39
 * To change this template use File | Settings | File Templates.
 */
public class ForumActivity extends DialogFragmentActivity {

    private static String TAG = "com.byr.assistant.ui.forum.ForumActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_activity);
        configureActionBar();

    }

    private void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.dropdown_forum);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.navigation_forum);
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
