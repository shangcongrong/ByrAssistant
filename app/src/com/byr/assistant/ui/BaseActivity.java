package com.byr.assistant.ui;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.byr.assistant.core.ViewFinder;

import java.io.Serializable;

/**
 * User: orange
 * Date: 13-9-5
 * Time: 上午10:15
 */
public class BaseActivity extends SherlockFragmentActivity {

    protected ViewFinder finder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finder = new ViewFinder(this);
    }

    protected <V extends Serializable> V getSerializableExtra(final String name) {
        return (V) getIntent().getSerializableExtra(name);
    }

    protected int getIntExtra(final String name) {
        return getIntent().getIntExtra(name, -1);
    }

    protected String getStringExtra(final String name) {
        return getIntent().getStringExtra(name);
    }

    protected String[] getStringArrayExtra(final String name) {
        return getIntent().getStringArrayExtra(name);
    }


}
