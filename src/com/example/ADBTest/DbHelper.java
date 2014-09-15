package com.example.ADBTest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yngvi on 15.9.2014.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "STUDENTS_DB";
    public static final int DB_VERSION = 1;

    public static final String TableStudent = "students";
    public static final String[] TableStudentCols = { "_id", "sid", "name", "cool" };

    private static final String sqlCreateTableStudent =
            "CREATE TABLE students(" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " sid INTEGER NOT NULL," +
            " name TEXT," +
            " cool INTEGER " +
            ");";

    private static final String sqlDropTableStudents =
            "DROP TABLE IF EXISTS students;";

    public DbHelper( Context context ) {
        super( context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( sqlCreateTableStudent );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( sqlDropTableStudents );
        onCreate( db );
    }
}
