package com.byr.assistant.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.byr.assistant.R;
import com.byr.assistant.core.SingleTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * User: orange
 * Date: 13-11-20
 * Time: 下午3:47
 */
public class HomeDropDownListAdapter extends SingleTypeAdapter<Object> {

    //一一对应
    public static final int ACTION_HOME = 0;

    public static final int ACTION_COURSE = 1;

    public static final int ACTION_EVNET = 2;

    public static final int ACTION_FORUM = 3;

    public static final int ACTION_NOTE = 4;


    private int selected = ACTION_HOME;

    private final LayoutInflater inflater;

    public HomeDropDownListAdapter(final Context context, List<NavigationListItem> navigations) {
        super(context, R.layout.home_navigation_list_item);
        inflater = LayoutInflater.from(context);
        setNavigations(navigations);

    }

    public int getNavigationCount() {
        return getCount();
    }


    public int getAction(final int position) {
        return position;
    }

    public HomeDropDownListAdapter setNavigations(List<NavigationListItem> navigations) {
        List<Object> all = new ArrayList<Object>();
        all.addAll(navigations);
        setItems(all);
        notifyDataSetChanged();
        return this;
    }

    public HomeDropDownListAdapter setSelected(int selected) {
        this.selected = selected;
        return this;
    }

    public int getSelected() {
        return selected;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_navigation, R.id.iv_navigation};
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = initialize(inflater.inflate(R.layout.home_navigation_list_item,
                    null));
        update(position, convertView, getItem(position));
        return convertView;
    }

    private void setActionIcon(ImageView image, int drawable) {
        image.setImageResource(drawable);
        image.setTag(R.id.iv_navigation, null);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    protected void update(int position, Object object) {
        NavigationListItem item = (NavigationListItem) object;
        setText(0, item.getNavigationStringId());
        setActionIcon(imageView(1), item.getNavigationDrawableId());
    }
}
