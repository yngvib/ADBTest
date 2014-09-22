package com.example.ADBTest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yngvi on 15.9.2014.
 */
public class StudentsAdapter {

    SQLiteDatabase db;
    DbHelper dbHelper;
    Context context;

    public StudentsAdapter( Context c ) {
        context = c;
    }

    public StudentsAdapter openToRead() {
        dbHelper = new DbHelper( context );
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public StudentsAdapter openToWrite() {
        dbHelper = new DbHelper( context );
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public long insertStudent( int sid, String name, boolean cool ) {
        String[] cols = DbHelper.TableStudentCols;
        ContentValues contentValues = new ContentValues();
        contentValues.put( cols[1], ((Integer)sid).toString() );
        contentValues.put( cols[2], name );
        contentValues.put( cols[3], cool ? "1" : "0" );
        openToWrite();
        long value = db.insert(DbHelper.TableStudent, null, contentValues );
        close();
        return value;
    }

    public long updateStudent( int sid, String name, boolean cool ) {
        String[] cols = DbHelper.TableStudentCols;
        ContentValues contentValues = new ContentValues();
        contentValues.put( cols[1], ((Integer)sid).toString() );
        contentValues.put( cols[2], name );
        contentValues.put( cols[3], cool ? "1" : "0" );
        openToWrite();
        long value = db.update(DbHelper.TableStudent,
                               contentValues,
                               cols[1] + "=" + sid, null );
        close();
        return value;
    }

    public long updateAllStudentsCoolness( boolean cool ) {
        String[] cols = DbHelper.TableStudentCols;
        ContentValues contentValues = new ContentValues();
        contentValues.put( cols[3], cool ? "1" : "0" );
        openToWrite();
        long value = db.update(DbHelper.TableStudent,
                contentValues,
                null, null );
        close();
        return value;
    }

    public long updateStudentCoolness( long id, boolean cool ) {
        String[] cols = DbHelper.TableStudentCols;
        ContentValues contentValues = new ContentValues();
        contentValues.put( cols[3], cool ? "1" : "0" );
        openToWrite();
        long value = db.update(DbHelper.TableStudent,
                contentValues,
                cols[0] + "=" + id, null );
        close();
        return value;
    }

    public Cursor queryStudents() {
        openToRead();
        Cursor cursor = db.query( DbHelper.TableStudent,
                                  DbHelper.TableStudentCols, null, null, null, null, null);
        return cursor;
    }

    public Cursor queryStudent( int sid) {
        openToRead();
        String[] cols = DbHelper.TableStudentCols;
        Cursor cursor = db.query( DbHelper.TableStudent,
                cols, cols[1] + "=" + sid, null, null, null, null);
        return cursor;
    }

}
