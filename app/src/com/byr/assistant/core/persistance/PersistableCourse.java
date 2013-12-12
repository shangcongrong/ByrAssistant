package com.byr.assistant.core.persistance;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.byr.assistant.core.model.Course;

import java.io.IOException;
import java.util.List;

import static com.byr.assistant.core.persistance.DbConstants.CourseConstants.*;

/**
 * User: orange
 * Date: 13-11-21
 * Time: 下午5:09
 */
public class PersistableCourse implements PersistableResource<Course> {


    @Override
    public String getTableName() {
        return TABLE_COUSERS;
    }

    public String[] getColumns() {
        return new String[]{COURSE_ID, COLLEGE, COURSE_TYPE, INTERVAL, COURSE_NAME, CREDIT, START_TIME, END_TIME, MAJOR, LOCATION, STUDENT_COUNT, TEACHER, START_DATE, END_DATE, WORKDAY, ADDED
        };
    }

    @Override
    public Cursor query(SQLiteDatabase readableDatabase) {
        return readableDatabase.query(getTableName(), getColumns(), null,
                null, null, null, null);
    }

    @Override
    public Cursor query(SQLiteDatabase readableDatabase, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return readableDatabase.query(getTableName(), getColumns(), selection,
                selectionArgs, groupBy, having, orderBy);
    }

    @Override
    public Course loadFrom(Cursor cursor) {
        Course course = new Course();
        course.setCourseId(cursor.getString(0));
        course.setCollege(cursor.getString(1));
        course.setCourseType(cursor.getString(2));
        course.setInterval(cursor.getInt(3));
        course.setCourseName(cursor.getString(4));
        course.setCredit(cursor.getInt(5));
        course.setStartTime(cursor.getString(6));
        course.setEndTime(cursor.getString(7));
        course.setMajor(cursor.getString(8));
        course.setLocation(cursor.getString(9));
        course.setStudentCount(cursor.getInt(10));
        course.setTeacher(cursor.getString(11));
        course.setStartDate(cursor.getString(12));
        course.setEndDate(cursor.getString(13));
        course.setWorkday(cursor.getInt(14));
        course.setAdded(cursor.getInt(15) == 1);
        return course;
    }

    @Override
    public void store(SQLiteDatabase writableDatabase, List<Course> items) {
        writableDatabase.delete(getTableName(), null, null);

        if (items == null || items.isEmpty())
            return;

        ContentValues values = new ContentValues();
        for (Course course : items) {
            values.clear();
            values.put(COURSE_ID, course.getCourseId());
            values.put(COLLEGE, course.getCollege());
            values.put(COURSE_TYPE, course.getCourseType());
            values.put(INTERVAL, course.getInterval());
            values.put(COURSE_NAME, course.getCourseName());
            values.put(CREDIT, course.getCredit());
            values.put(START_TIME, course.getStartTime());
            values.put(END_TIME, course.getEndTime());
            values.put(MAJOR, course.getMajor());
            values.put(LOCATION, course.getLocation());
            values.put(TEACHER, course.getTeacher());
            values.put(START_DATE, course.getStartDate());
            values.put(END_DATE, course.getEndDate());
            values.put(WORKDAY, course.getWorkday());
            values.put(ADDED, course.isAdded() ? 1 : 0);
            writableDatabase.insert(getTableName(), null, values);
        }
    }

    @Override
    public List<Course> request() throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void insert(SQLiteDatabase writableDatabase, Course course) {
        ContentValues values = new ContentValues();
        values.put(COURSE_ID, course.getCourseId());
        values.put(COLLEGE, course.getCollege());
        values.put(COURSE_TYPE, course.getCourseType());
        values.put(INTERVAL, course.getInterval());
        values.put(COURSE_NAME, course.getCourseName());
        values.put(CREDIT, course.getCredit());
        values.put(START_TIME, course.getStartTime());
        values.put(END_TIME, course.getEndTime());
        values.put(MAJOR, course.getMajor());
        values.put(LOCATION, course.getLocation());
        values.put(TEACHER, course.getTeacher());
        values.put(START_DATE, course.getStartDate());
        values.put(END_DATE, course.getEndDate());
        values.put(WORKDAY, course.getWorkday());
        values.put(ADDED, course.isAdded() ? 1 : 0);
        writableDatabase.insert(getTableName(), null, values);
    }

    @Override
    public void update(SQLiteDatabase writableDatabase, Course course) {
        ContentValues values = new ContentValues();
        values.put(COURSE_ID, course.getCourseId());
        values.put(COLLEGE, course.getCollege());
        values.put(COURSE_TYPE, course.getCourseType());
        values.put(INTERVAL, course.getInterval());
        values.put(COURSE_NAME, course.getCourseName());
        values.put(CREDIT, course.getCredit());
        values.put(START_TIME, course.getStartTime());
        values.put(END_TIME, course.getEndTime());
        values.put(MAJOR, course.getMajor());
        values.put(LOCATION, course.getLocation());
        values.put(TEACHER, course.getTeacher());
        values.put(START_DATE, course.getStartDate());
        values.put(END_DATE, course.getEndDate());
        values.put(WORKDAY, course.getWorkday());
        values.put(ADDED, course.isAdded() ? 1 : 0);
        writableDatabase.update(getTableName(), values, "courseId=?", new String[]{course.getCourseId()});
    }

    @Override
    public void delete(SQLiteDatabase writableDatabase, Course item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clear(SQLiteDatabase writableDatabase, String tableName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
