package com.byr.assistant.core.persistance;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.util.List;

/**
 * Describes how to store, load or request-an-update-for a particular set of data
 * <p/>
 *
 * @param <E> User: orange
 *            Date: 13-10-22
 *            Time: 上午1:30
 */
public abstract interface PersistableResource<E> {


    abstract String getTableName();

    abstract String[] getColumns();


    Cursor query(SQLiteDatabase readableDatabase);


    Cursor query(SQLiteDatabase readableDatabase,
                 String selection, String[] selectionArgs, String groupBy, String having, String orderBy);


    E loadFrom(Cursor cursor);


    void store(SQLiteDatabase writableDatabase, List<E> items);


    List<E> request() throws IOException;


    void insert(SQLiteDatabase writableDatabase, E item);


    void update(SQLiteDatabase writableDatabase, E item);


    void delete(SQLiteDatabase writableDatabase, E item);


    void clear(SQLiteDatabase writableDatabase, String tableName);


}
