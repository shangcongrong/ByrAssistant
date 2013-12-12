package com.byr.assistant.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.byr.assistant.R;
import com.byr.assistant.core.SingleTypeAdapter;
import com.byr.assistant.core.ViewUtils;

import java.util.Collections;
import java.util.List;

/**
 * User: orange
 * Date: 13-11-19
 * Time: 下午3:47
 */
public abstract class ItemListFragment<E> extends DialogFragment {

    private static final String FORCE_REFRESH = "forceRefresh";

    protected static boolean isForceRefresh(Bundle args) {
        return args != null && args.getBoolean(FORCE_REFRESH, false);
    }

    protected List<E> items = Collections.emptyList();

    protected ListView listView;

    protected TextView emptyView;

    protected ProgressBar progressBar;

    protected boolean listShown;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!items.isEmpty())
            setListShown(true, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.default_list_fragment, null);
    }

    @Override
    public void onDestroyView() {
        listShown = false;
        emptyView = null;
        progressBar = null;
        listView = null;

        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) view.findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                onListItemClick((ListView) parent, view, position, id);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                return onListItemLongClick((ListView) parent, view, position,
                        id);
            }
        });
        progressBar = (ProgressBar) view.findViewById(R.id.pb_loading);

        emptyView = (TextView) view.findViewById(android.R.id.empty);

        configureList(getActivity(), getListView());
    }

    protected void configureList(Activity activity, ListView listView) {
        listView.setAdapter(createAdapter());
    }

    @Override
    public void onCreateOptionsMenu(Menu optionsMenu, MenuInflater inflater) {
        inflater.inflate(R.menu.refresh, optionsMenu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!isUsable())
            return false;

        switch (item.getItemId()) {
            case R.id.m_refresh:
                forceRefresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void forceRefresh() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(FORCE_REFRESH, true);
        refresh(bundle);
    }

    public void refresh() {
        refresh(null);
    }

    private void refresh(final Bundle args) {
        if (!isUsable())
            return;

        getSherlockActivity()
                .setSupportProgressBarIndeterminateVisibility(true);
    }


    public void onLoadFinished(List<E> items) {
        if (!isUsable())
            return;

        getSherlockActivity().setSupportProgressBarIndeterminateVisibility(
                false);
        //TODO 错误处理

        this.items = items;
        getListAdapter().getWrappedAdapter().setItems(items.toArray());
        showList();
    }

    protected HeaderFooterListAdapter<SingleTypeAdapter<E>> createAdapter() {
        SingleTypeAdapter<E> wrapped = createAdapter(items);
        return new HeaderFooterListAdapter<SingleTypeAdapter<E>>(getListView(),
                wrapped);
    }

    protected abstract SingleTypeAdapter<E> createAdapter(final List<E> items);

    protected void showList() {
        setListShown(true, isResumed());
    }

    protected void refreshWithProgress() {
        items.clear();
        setListShown(false);
        refresh();
    }

    public ListView getListView() {
        return listView;
    }

    @SuppressWarnings("unchecked")
    protected HeaderFooterListAdapter<SingleTypeAdapter<E>> getListAdapter() {
        if (listView != null)
            return (HeaderFooterListAdapter<SingleTypeAdapter<E>>) listView
                    .getAdapter();
        else
            return null;
    }

    protected ItemListFragment<E> notifyDataSetChanged() {
        HeaderFooterListAdapter<SingleTypeAdapter<E>> root = getListAdapter();
        if (root != null) {
            SingleTypeAdapter<E> typeAdapter = root.getWrappedAdapter();
            if (typeAdapter != null)
                typeAdapter.notifyDataSetChanged();
        }
        return this;
    }

    protected ItemListFragment<E> setListAdapter(final ListAdapter adapter) {
        if (listView != null)
            listView.setAdapter(adapter);
        return this;
    }

    private ItemListFragment<E> fadeIn(final View view, final boolean animate) {
        if (view != null)
            if (animate)
                view.startAnimation(AnimationUtils.loadAnimation(getActivity(),
                        android.R.anim.fade_in));
            else
                view.clearAnimation();
        return this;
    }

    private ItemListFragment<E> show(final View view) {
        ViewUtils.setGone(view, false);
        return this;
    }

    private ItemListFragment<E> hide(final View view) {
        ViewUtils.setGone(view, true);
        return this;
    }

    public ItemListFragment<E> setListShown(final boolean shown) {
        return setListShown(shown, true);
    }

    public ItemListFragment<E> setListShown(final boolean shown,
                                            final boolean animate) {
        if (!isUsable())
            return this;

        if (shown == listShown) {
            if (shown)
                // List has already been shown so hide/show the empty view with
                // no fade effect
                if (items.isEmpty())
                    hide(listView).show(emptyView);
                else
                    hide(emptyView).show(listView);
            return this;
        }

        listShown = shown;

        if (shown)
            if (!items.isEmpty())
                hide(progressBar).hide(emptyView).fadeIn(listView, animate)
                        .show(listView);
            else
                hide(progressBar).hide(listView).fadeIn(emptyView, animate)
                        .show(emptyView);
        else
            hide(listView).hide(emptyView).fadeIn(progressBar, animate)
                    .show(progressBar);

        return this;
    }

    protected ItemListFragment<E> setEmptyText(final String message) {
        if (emptyView != null) {
            emptyView.setText(message);
            hide(progressBar).show(emptyView);
        }
        return this;
    }

    protected ItemListFragment<E> setEmptyText(final int resId) {
        if (emptyView != null)
            emptyView.setText(resId);
        return this;
    }


    public void onListItemClick(ListView l, View v, int position, long id) {
    }


    public boolean onListItemLongClick(ListView l, View v, int position, long id) {
        return false;
    }
}
