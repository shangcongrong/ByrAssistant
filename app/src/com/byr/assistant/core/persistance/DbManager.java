package com.byr.assistant.core.persistance;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * User: orange
 * Date: 13-11-25
 * Time: 下午7:15
 */
public class DbManager {

    private static final String TAG = "DbManager";

    private static DbManager instance = null;

    private DbCache dbCache;

    public static DbManager getInstance(Context context) {
        if (null == instance) {
            instance = new DbManager(context);
        }
        return instance;
    }

    private DbManager(Context context) {
        dbCache = new DbCache(context);
    }

    public DbCache getDatabaseCache() {
        return dbCache;
    }

    public Cursor query(SQLiteOpenHelper helper, String tables,
                        String[] columns) {
        return query(helper, tables, columns, null, null);
    }

    public Cursor query(SQLiteOpenHelper helper, String tables,
                        String[] columns, String selection, String[] selectionArgs) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(tables);
        return builder.query(helper.getReadableDatabase(), columns, selection,
                selectionArgs, null, null, null);
    }


}
