package com.byr.assistant.ui.course;

import android.app.Activity;
import com.byr.assistant.R;
import com.byr.assistant.core.SingleTypeAdapter;
import com.byr.assistant.core.model.Course;

import java.util.List;

/**
 * Created by orange on 13-11-27.
 */
public class AddCourseAdapter extends SingleTypeAdapter<Course> {

    public AddCourseAdapter(Activity activity, List<Course> list) {
        super(activity, R.layout.course_add_list_item);
        setItems(list);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_course_time, R.id.tv_course_name, R.id.tv_course_location, R.id.tv_course_teacher, R.id.cb_select_course};
    }

    @Override
    protected void update(int position, Course course) {
        setText(0, course.getStartTime() + " 至 " + course.getEndTime());
        setText(1, course.getCourseName());
        setText(2, course.getLocation());
        setText(3, course.getTeacher());
        setChecked(4, course.isAdded());
    }

    public void updateCheckBoxState(int position) {
        Course course = getItem(position);
        course.setAdded(!course.isAdded());
        update(position, course);
    }


}
