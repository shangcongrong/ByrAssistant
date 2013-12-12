package com.byr.assistant.core.persistance;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.byr.assistant.core.model.Note;

import java.io.IOException;
import java.util.List;

import static com.byr.assistant.core.persistance.DbConstants.NoteConstants.*;

/**
 * User: orange
 * Date: 13-11-21
 * Time: 下午5:17
 */
public class PersistableNote implements PersistableResource<Note> {
    @Override
    public String getTableName() {
        return TABLE_NOTE;
    }

    @Override
    public String[] getColumns() {
        return new String[]{NOTE_ID, HEADLINE, CONTENT, EDIT_TIME};
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
    public Note loadFrom(Cursor cursor) {
        Note note = new Note();
        note.setNoteId(cursor.getInt(0));
        note.setHeadLine(cursor.getString(1));
        note.setContent(cursor.getString(2));
        note.setEditDate(cursor.getString(3));
        return note;
    }

    @Override
    public void store(SQLiteDatabase writableDatabase, List<Note> items) {

        writableDatabase.delete(getTableName(), null, null);

        if (items == null || items.isEmpty())
            return;

        ContentValues values = new ContentValues();
        for (Note note : items) {
            values.clear();
            values.put(NOTE_ID, note.getNoteId());
            values.put(HEADLINE, note.getHeadLine());
            values.put(CONTENT, note.getContent());
            values.put(EDIT_TIME, note.getEditDate());
            writableDatabase.insert(getTableName(), null, values);
        }
    }

    @Override
    public List<Note> request() throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void insert(SQLiteDatabase writableDatabase, Note note) {

        ContentValues values = new ContentValues();
        values.clear();
        values.put(HEADLINE, note.getHeadLine());
        values.put(CONTENT, note.getContent());
        values.put(EDIT_TIME, note.getEditDate());
        writableDatabase.insert(getTableName(), null, values);

    }

    @Override
    public void update(SQLiteDatabase writableDatabase, Note note) {

        ContentValues values = new ContentValues();
        values.clear();
        values.put(HEADLINE, note.getHeadLine());
        values.put(CONTENT, note.getContent());
        values.put(EDIT_TIME, note.getEditDate());
        writableDatabase.update(getTableName(), values, NOTE_ID + "=?", new String[]{String.valueOf(note.getNoteId())});
    }

    @Override
    public void delete(SQLiteDatabase writableDatabase, Note item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clear(SQLiteDatabase writableDatabase, String tableName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
