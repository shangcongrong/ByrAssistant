package com.byr.assistant.ui;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import com.byr.assistant.R;

/**
 * User: orange
 * Date: 13-11-19
 * Time: 上午11:18
 */
public abstract class TitleActivity<V extends PagerAdapter & FragmentProvider> extends PageActivity implements TitlePageIndicator.OnCenterItemClickListener {

    protected ViewPager mPager;

    protected TitlePageIndicator mIndicator;

    protected V adapter;

    @Override
    public void onPageSelected(final int position) {
        super.onPageSelected(position);
    }

    protected abstract V createAdapter();


    protected int getContentView() {
        return R.layout.default_title_activity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());

        mPager = (ViewPager) findViewById(R.id.viewPager);
        mPager.setOnPageChangeListener(this);
        mPager.setAdapter(createAdapter());


        TitlePageIndicator indicator = (TitlePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        indicator.setFooterIndicatorStyle(TitlePageIndicator.IndicatorStyle.Underline);
        indicator.setOnCenterItemClickListener(this);
        mIndicator = indicator;
    }

    @Override
    protected FragmentProvider getProvider() {
        return adapter;
    }
}