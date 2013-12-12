package com.byr.assistant.ui;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.ListView.FixedViewInfo;

import java.util.ArrayList;

/**
 * User: orange
 * Date: 13-11-19
 * Time: 下午3:56
 */

/**
 * Utility adapter that supports adding headers and footers
 *
 * @param <E>
 */
public class HeaderFooterListAdapter<E extends BaseAdapter> extends
        HeaderViewListAdapter {

    private final ListView list;

    private final ArrayList<ListView.FixedViewInfo> headers;

    private final ArrayList<ListView.FixedViewInfo> footers;

    private final E wrapped;

    /**
     * Create header footer adapter
     *
     * @param view
     * @param adapter
     */
    public HeaderFooterListAdapter(ListView view, E adapter) {
        this(new ArrayList<ListView.FixedViewInfo>(), new ArrayList<ListView.FixedViewInfo>(),
                view, adapter);
    }

    private HeaderFooterListAdapter(ArrayList<ListView.FixedViewInfo> headerViewInfos,
                                    ArrayList<ListView.FixedViewInfo> footerViewInfos, ListView view, E adapter) {
        super(headerViewInfos, footerViewInfos, adapter);

        headers = headerViewInfos;
        footers = footerViewInfos;
        list = view;
        wrapped = adapter;
    }

    /**
     * Add non-selectable header view with no data
     *
     * @see #addHeader(android.view.View, Object, boolean)
     * @param view
     * @return this adapter
     */
    public HeaderFooterListAdapter<E> addHeader(View view) {
        return addHeader(view, null, false);
    }

    /**
     * Add header
     *
     * @param view
     * @param data
     * @param isSelectable
     * @return this adapter
     */
    public HeaderFooterListAdapter<E> addHeader(View view, Object data,
                                                boolean isSelectable) {
        ListView.FixedViewInfo info = list.new FixedViewInfo();
        info.view = view;
        info.data = data;
        info.isSelectable = isSelectable;

        headers.add(info);
        wrapped.notifyDataSetChanged();
        return this;
    }

    /**
     * Add non-selectable footer view with no data
     *
     * @see #addFooter(View, Object, boolean)
     * @param view
     * @return this adapter
     */
    public HeaderFooterListAdapter<E> addFooter(View view) {
        return addFooter(view, null, false);
    }

    /**
     * Add footer
     *
     * @param view
     * @param data
     * @param isSelectable
     * @return this adapter
     */
    public HeaderFooterListAdapter<E> addFooter(View view, Object data,
                                                boolean isSelectable) {
        ListView.FixedViewInfo info = list.new FixedViewInfo();
        info.view = view;
        info.data = data;
        info.isSelectable = isSelectable;

        footers.add(info);
        wrapped.notifyDataSetChanged();
        return this;
    }

    @Override
    public boolean removeHeader(View v) {
        boolean removed = super.removeHeader(v);
        if (removed)
            wrapped.notifyDataSetChanged();
        return removed;
    }

    /**
     * Remove all headers
     *
     * @return true if headers were removed, false otherwise
     */
    public boolean clearHeaders() {
        boolean removed = false;
        if (!headers.isEmpty()) {
            ListView.FixedViewInfo[] infos = headers.toArray(new ListView.FixedViewInfo[headers
                    .size()]);
            for (ListView.FixedViewInfo info : infos)
                removed = super.removeHeader(info.view) || removed;
        }
        if (removed)
            wrapped.notifyDataSetChanged();
        return removed;
    }

    /**
     * Remove all footers
     *
     * @return true if headers were removed, false otherwise
     */
    public boolean clearFooters() {
        boolean removed = false;
        if (!footers.isEmpty()) {
            ListView.FixedViewInfo[] infos = footers.toArray(new ListView.FixedViewInfo[footers
                    .size()]);
            for (ListView.FixedViewInfo info : infos)
                removed = super.removeFooter(info.view) || removed;
        }
        if (removed)
            wrapped.notifyDataSetChanged();
        return removed;
    }

    @Override
    public boolean removeFooter(View v) {
        boolean removed = super.removeFooter(v);
        if (removed)
            wrapped.notifyDataSetChanged();
        return removed;
    }

    @Override
    public E getWrappedAdapter() {
        return wrapped;
    }

    @Override
    public boolean isEmpty() {
        return wrapped.isEmpty();
    }
}
