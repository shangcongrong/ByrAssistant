package com.byr.assistant.core.persistance;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.byr.assistant.core.model.Event;

import java.io.IOException;
import java.util.List;

import static com.byr.assistant.core.persistance.DbConstants.EventConstants.*;

/**
 * User: orange
 * Date: 13-11-21
 * Time: 下午5:17
 */
public class PersistableEvent implements PersistableResource<Event> {

    @Override
    public String getTableName() {
        return TABLE_EVENT;
    }

    @Override
    public String[] getColumns() {
        return new String[]{EVENT_ID, EVENT_NAME, TIME, LOCATION, DEPARTMENT, CONTENT};
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
    public Event loadFrom(Cursor cursor) {
        Event event = new Event();
        event.setEventId(cursor.getString(0));
        event.setEventName(cursor.getString(1));
        event.setTime(cursor.getString(2));
        event.setLocation(cursor.getString(3));
        event.setDepartment(cursor.getString(4));
        event.setContent(cursor.getString(5));
        return event;
    }

    @Override
    public void store(SQLiteDatabase writableDatabase, List<Event> items) {
        writableDatabase.delete(getTableName(), null, null);

        if (items == null || items.isEmpty())
            return;

        ContentValues values = new ContentValues();
        for (Event event : items) {
            values.clear();
            values.put(EVENT_ID, event.getEventId());
            values.put(EVENT_NAME, event.getEventName());
            values.put(TIME, event.getTime());
            values.put(LOCATION, event.getLocation());
            values.put(DEPARTMENT, event.getDepartment());
            values.put(CONTENT, event.getContent());
            writableDatabase.insert(getTableName(), null, values);
        }
    }

    @Override
    public List<Event> request() throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void insert(SQLiteDatabase writableDatabase, Event event) {

        ContentValues values = new ContentValues();
        values.clear();
        values.put(EVENT_ID, event.getEventId());
        values.put(EVENT_NAME, event.getEventName());
        values.put(TIME, event.getTime());
        values.put(LOCATION, event.getLocation());
        values.put(DEPARTMENT, event.getDepartment());
        values.put(CONTENT, event.getContent());
        writableDatabase.insert(getTableName(), null, values);
    }

    @Override
    public void update(SQLiteDatabase writableDatabase, Event item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(SQLiteDatabase writableDatabase, Event item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clear(SQLiteDatabase writableDatabase, String tableName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
