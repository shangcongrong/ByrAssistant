package com.byr.assistant.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.byr.assistant.R;
import com.byr.assistant.core.model.Course;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-30
 * Time: 下午1:32
 * To change this template use File | Settings | File Templates.
 */
public class HomeCellCourse extends HomeCellBase<Course> {

    private TextView tvName;

    private TextView tvTime;

    private TextView tvLocation;

    private TextView tvTeacher;

    private TextView tvEmptyText;

    private LinearLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_layout_course, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvName = (TextView) view.findViewById(R.id.tv_course_name);
        tvLocation = (TextView) view.findViewById(R.id.tv_course_location);
        tvTeacher = (TextView) view.findViewById(R.id.tv_course_teacher);
        tvTime = (TextView) view.findViewById(R.id.tv_course_time);
        tvEmptyText = (TextView) view.findViewById(R.id.tv_empty_text);
        layout = (LinearLayout) view.findViewById(R.id.layout_home_course);
    }

    @Override
    HomeCellBase<Course> loadData(Course course) {
        if (course != null) {
            tvName.setText(course.getCourseName());
            tvLocation.setText(course.getLocation());
            tvTeacher.setText(course.getTeacher());
            tvTime.setText(course.getStartTime() + " 至 " + course.getEndTime());
            showData();
        } else {
            showEmptyText();
        }
        return this;
    }

    @Override
    void showEmptyText() {
        tvEmptyText.setVisibility(View.VISIBLE);
        tvEmptyText.setText("今日没有课");
        layout.setVisibility(View.GONE);
    }

    @Override
    void showData() {
        tvEmptyText.setVisibility(View.GONE);
        layout.setVisibility(View.GONE);
    }

}
