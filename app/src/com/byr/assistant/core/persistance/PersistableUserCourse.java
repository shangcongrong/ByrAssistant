package com.byr.assistant.core.persistance;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.byr.assistant.core.model.Course;

import java.io.IOException;
import java.util.List;

/**
 * User: orange
 * Date: 13-11-25
 * Time: 下午8:49
 */
public class PersistableUserCourse extends PersistableCourse {

    @Override
    public String getTableName() {
        return DbConstants.CourseConstants.TABLE_USER_COURSES;
    }

}
