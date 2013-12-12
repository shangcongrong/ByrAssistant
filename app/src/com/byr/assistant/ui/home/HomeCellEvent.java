package com.byr.assistant.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.byr.assistant.R;
import com.byr.assistant.core.model.Event;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-30
 * Time: 下午1:32
 * To change this template use File | Settings | File Templates.
 */
public class HomeCellEvent extends HomeCellBase<Event> {

    private TextView tvName;

    private TextView tvTime;

    private TextView tvLocation;

    private TextView tvDepartment;

    private TextView tvContent;

    private LinearLayout layout;

    private TextView tvEmptyText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_layout_event, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvContent = (TextView) view.findViewById(R.id.tv_event_content);
        tvTime = (TextView) view.findViewById(R.id.tv_event_time);
        tvLocation = (TextView) view.findViewById(R.id.tv_event_location);
        tvDepartment = (TextView) view.findViewById(R.id.tv_event_department);
        tvName = (TextView) view.findViewById(R.id.tv_event_name);
        layout = (LinearLayout) view.findViewById(R.id.layout_home_event);
        tvEmptyText = (TextView) view.findViewById(R.id.tv_empty_text);
    }

    @Override
    HomeCellBase<Event> loadData(Event event) {
        if (event != null) {
            tvContent.setText(event.getContent());
            tvName.setText(event.getEventName());
            tvLocation.setText(event.getLocation());
            tvDepartment.setText(event.getDepartment());
            tvTime.setText(event.getTime());
            showData();
        } else {
            showEmptyText();
        }
        return this;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void showEmptyText() {
        tvEmptyText.setText("今日无活动哦~");
        tvEmptyText.setVisibility(View.VISIBLE);
        layout.setVisibility(View.GONE);
    }


    @Override
    void showData() {
        tvEmptyText.setVisibility(View.GONE);
        layout.setVisibility(View.VISIBLE);
    }
}
