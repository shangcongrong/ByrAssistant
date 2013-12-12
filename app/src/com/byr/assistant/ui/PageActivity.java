package com.byr.assistant.ui;

import android.support.v4.view.ViewPager;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * User: orange
 * Date: 13-11-19
 * Time: 上午10:58
 */
public abstract class PageActivity extends DialogFragmentActivity implements ViewPager.OnPageChangeListener {


    private boolean menuCreated;

    protected abstract FragmentProvider getProvider();

    protected SherlockFragment getFragment() {
        FragmentProvider provider = getProvider();
        if (provider != null)
            return provider.getSelected();
        else
            return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SherlockFragment fragment = getFragment();
        if (fragment != null)
            return fragment.onOptionsItemSelected(item);
        else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public void invalidateOptionsMenu() {
        if (menuCreated)
            super.invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SherlockFragment fragment = getFragment();
        if (fragment != null)
            fragment.onCreateOptionsMenu(menu, getSupportMenuInflater());

        boolean created = super.onCreateOptionsMenu(menu);
        menuCreated = true;
        return created;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPageSelected(int position) {
        invalidateOptionsMenu();
        ;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
