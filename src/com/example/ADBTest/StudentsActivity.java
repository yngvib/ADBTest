package com.example.ADBTest;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by yngvi on 15.9.2014.
 */
public class StudentsActivity extends ListActivity {

    private StudentsAdapter mSA = new StudentsAdapter( this );
    private SimpleCursorAdapter mCA;
    private Cursor mCursor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCursor = mSA.queryStudents();
        String cols[] = DbHelper.TableStudentCols;
        String from[] = { cols[1], cols[2], cols[3] };
        int to[] = { R.id.s_sid, R.id.s_name, R.id.s_cool };
        startManagingCursor( mCursor );
        mCA = new SimpleCursorAdapter(this, R.layout.row, mCursor, from, to );

        mCA.setViewBinder( new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if ( columnIndex == 3 ) {
                    ((ImageView)view).setImageResource(
                            (cursor.getInt(columnIndex) == 0) ?
                                   R.drawable.emo_im_sad : R.drawable.emo_im_cool );
                    return true;
                }
                return false;
            }
        });

        setListAdapter( mCA );
    }

    @Override
    protected void onListItemClick( ListView l, View v, int position, long id ) {
        Toast.makeText(getApplicationContext(),"p=" + position + " id=" + id, Toast.LENGTH_LONG ).show();
        mSA.updateStudentCoolness( id, true );
        mCursor.requery();
    }


}