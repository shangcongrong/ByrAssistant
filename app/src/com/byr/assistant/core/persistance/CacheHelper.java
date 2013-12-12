package com.byr.assistant.core.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.inject.Inject;

/**
 * User: orange
 * Date: 13-10-22
 * Time: 上午1:52
 */
public class CacheHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;

    private static final String NAME = "cache";

    public CacheHelper(final Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE courses (courseId TEXT PRIMARY KEY, college TEXT, courseType TEXT, interval INTEGER, courseName TEXT, credit INTEGER, startTime TEXT, endTime TEXT, major TEXT, location TEXT, studentCount INTEGER, teacher TEXT,startDate TEXT, endDate TEXT, workday TEXT, added, INTEGER);");
        db.execSQL("CREATE TABLE userCourses (courseId TEXT PRIMARY KEY, college TEXT, courseType TEXT, interval INTEGER, courseName TEXT, credit INTEGER, startTime TEXT, endTime TEXT, major TEXT, location TEXT, studentCount INTEGER, teacher TEXT,startDate TEXT, endDate TEXT, workday TEXT, added, INTEGER);");
        db.execSQL("CREATE TABLE forums (forumId TEXT PRIMARY KEY, author TEXT, title TEXT, articleUrl TEXT, publishDate TEXT, content TEXT);");
        db.execSQL("CREATE TABLE events (eventId TEXT PRIMARY KEY, eventName TEXT, time TEXT, location TEXT, department TEXT, content TEXT);");
        db.execSQL("CREATE TABLE notes (_id INTEGER PRIMARY KEY AUTOINCREMENT, headLine TEXT, content TEXT, editTime TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS courses");
        db.execSQL("DROP TABLE IF EXISTS userCourses");
        db.execSQL("DROP TABLE IF EXISTS forums");
        db.execSQL("DROP TABLE IF EXISTS events");
        db.execSQL("DROP TABLE IF EXISTS notes");
    }

}
