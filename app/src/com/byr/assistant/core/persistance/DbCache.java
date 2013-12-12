package com.byr.assistant.core.persistance;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: orange
 * Date: 13-11-25
 * Time: 下午7:02
 */
public class DbCache {

    private static final String TAG = "DbCache";

    private final CacheHelper helper;

    public DbCache(Context context) {
        helper = new CacheHelper(context);
    }

    public interface Transaction {
        void perform(SQLiteDatabase database);
    }

    protected SQLiteDatabase getReadable() {
        try {
            return helper.getReadableDatabase();
        } catch (SQLiteException e1) {
            try {
                return helper.getReadableDatabase();
            } catch (SQLiteException e2) {
                return null;
            }
        }
    }

    protected SQLiteDatabase getWritable() {
        try {
            return helper.getWritableDatabase();
        } catch (SQLiteException e1) {
            try {
                return helper.getWritableDatabase();
            } catch (SQLiteException e2) {
                return null;
            }
        }
    }

    protected boolean run(final Transaction transaction) {
        final SQLiteDatabase database = helper.getWritableDatabase();
        if (database == null)
            return false;

        database.beginTransaction();

        try {
            transaction.perform(database);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
        return true;
    }

    public <E> void store(
            final PersistableResource<E> persistableResource, List<E> items)
            throws IOException {

        final SQLiteDatabase db = getWritable();

        if (db == null)
            return;

        db.beginTransaction();
        try {
            persistableResource.store(db, items);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            helper.close();
        }
    }

    public <E> List<E> loadFromDB(
            final PersistableResource<E> persistableResource) {
        return loadFromDB(persistableResource, null, null, null, null, null);
    }

    public <E> List<E> loadFromDB(final PersistableResource<E> persistableResource,
                                  String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        final SQLiteDatabase db = getReadable();
        if (db == null)
            return null;

        Cursor cursor = persistableResource.query(db, selection, selectionArgs, groupBy, having, orderBy);
        try {
            if (!cursor.moveToFirst())
                return null;

            List<E> cached = new ArrayList<E>();
            do
                cached.add(persistableResource.loadFrom(cursor));
            while (cursor.moveToNext());
            return cached;
        } finally {
            cursor.close();
            helper.close();
        }
    }

    public <E> void insert(PersistableResource<E> persistableResource, E item) {
        final SQLiteDatabase db = helper.getWritableDatabase();
        if (db == null) {
            return;
        }
        db.beginTransaction();
        try {
            persistableResource.insert(db, item);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            helper.close();
        }
    }

    public <E> void update(PersistableResource<E> persistableResource, E item) {
        final SQLiteDatabase db = helper.getWritableDatabase();
        if (db == null) {
            return;
        }
        db.beginTransaction();
        try {
            persistableResource.update(db, item);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            helper.close();
        }
    }

    public <E> void delete(PersistableResource<E> persistableResource, E item) {
        final SQLiteDatabase db = helper.getWritableDatabase();
        if (db == null) {
            return;
        }
        db.beginTransaction();
        try {
            persistableResource.delete(db, item);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            helper.close();
        }
    }

}
