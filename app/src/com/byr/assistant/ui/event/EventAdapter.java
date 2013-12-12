package com.byr.assistant.ui.event;

import android.app.Activity;
import com.byr.assistant.R;
import com.byr.assistant.core.SingleTypeAdapter;
import com.byr.assistant.core.model.Event;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-29
 * Time: 下午8:17
 * To change this template use File | Settings | File Templates.
 */
public class EventAdapter extends SingleTypeAdapter<Event> {
    public EventAdapter(Activity activity, List<Event> list) {
        super(activity, R.layout.event_item_item);
        setItems(list);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_event_name, R.id.tv_event_time, R.id.tv_event_location, R.id.tv_event_content, R.id.tv_event_department};
    }

    @Override
    protected void update(int position, Event event) {
        setText(0, event.getEventName());
        setText(1, event.getTime());
        setText(2, event.getLocation());
        setText(3, event.getContent());
        setText(4, event.getDepartment());
    }
}
