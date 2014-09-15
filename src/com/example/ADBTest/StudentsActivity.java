package com.example.ADBTest;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

/**
 * Created by yngvi on 15.9.2014.
 */
public class StudentsActivity extends ListActivity {

    private StudentsAdapter mSA = new StudentsAdapter( this );
    private SimpleCursorAdapter mCA;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Cursor cursor = mSA.queryStudents();
        String cols[] = DbHelper.TableStudentCols;
        String from[] = { cols[1], cols[2], cols[3] };
        int to[] = { R.id.s_sid, R.id.s_name, R.id.s_cool };
        startManagingCursor( cursor );
        mCA = new SimpleCursorAdapter(this, R.layout.row, cursor, from, to );

        setListAdapter( mCA );
    }
}