package com.byr.assistant.core.persistance;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.byr.assistant.core.model.Forum;

import java.io.IOException;
import java.util.List;

import static com.byr.assistant.core.persistance.DbConstants.ForumConstants.*;
import static com.byr.assistant.core.persistance.DbConstants.ForumConstants.ARTICLE_URL;

/**
 * Created with IntelliJ IDEA.
 * User: orange
 * Date: 13-11-29
 * Time: 下午6:54
 * To change this template use File | Settings | File Templates.
 */
public class PersistableForum implements PersistableResource<Forum> {
    @Override
    public String getTableName() {
        return TABLE_FORUM;
    }


    @Override
    public String[] getColumns() {
        return new String[]{FORUM_ID,
                TITLE, ARTICLE_URL, AUTHOR, CONTENT, PUBLISH_DATE
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
    public Forum loadFrom(Cursor cursor) {

        Forum forum = new Forum();
        forum.setForumId(cursor.getString(0));
        forum.setTitle(cursor.getString(1));
        forum.setArticleUrl(cursor.getString(2));
        forum.setAuthor(cursor.getString(3));
        forum.setContent(cursor.getString(4));
        forum.setPublishDate(cursor.getString(5));
        return forum;
    }

    @Override
    public void store(SQLiteDatabase writableDatabase, List<Forum> items) {
        writableDatabase.delete(getTableName(), null, null);

        if (items == null || items.isEmpty())
            return;

        ContentValues values = new ContentValues();
        for (Forum forum : items) {
            values.clear();
            values.put(FORUM_ID, forum.getForumId());
            values.put(TITLE, forum.getTitle());
            values.put(AUTHOR, forum.getAuthor());
            values.put(ARTICLE_URL, forum.getArticleUrl());
            values.put(CONTENT, forum.getContent());
            values.put(PUBLISH_DATE, forum.getPublishDate());
            writableDatabase.insert(getTableName(), null, values);
        }
    }

    @Override
    public List<Forum> request() throws IOException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void insert(SQLiteDatabase writableDatabase, Forum forum) {

        ContentValues values = new ContentValues();
        values.clear();
        values.put(FORUM_ID, forum.getForumId());
        values.put(TITLE, forum.getTitle());
        values.put(AUTHOR, forum.getAuthor());
        values.put(ARTICLE_URL, forum.getArticleUrl());
        values.put(CONTENT, forum.getContent());
        values.put(PUBLISH_DATE, forum.getPublishDate());
        writableDatabase.insert(getTableName(), null, values);
    }

    @Override
    public void update(SQLiteDatabase writableDatabase, Forum item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(SQLiteDatabase writableDatabase, Forum item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clear(SQLiteDatabase writableDatabase, String tableName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
