package com.example.ADBTest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by yngvi on 22.9.2014.
 */
public class OptionsActivity extends PreferenceActivity {

    final int DIALOG_CONFIRM_UNCOOL = 20;

    StudentsAdapter mSA;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource( R.xml.settings );
        mSA = new StudentsAdapter( this );
        Preference pref = (Preference) findPreference("pref_uncool");
        pref.setOnPreferenceClickListener( new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                dispDialog( DIALOG_CONFIRM_UNCOOL );
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
       super.onDestroy();
       mSA.close();
    }
    
    private void dispDialog( int id ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch ( id ) {
            case DIALOG_CONFIRM_UNCOOL:
                builder.setMessage("Are you sure?");
                builder.setCancelable(true);
                builder.setPositiveButton( "yes", new resetClass() );
                builder.setNegativeButton( "no", null );
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private final class resetClass implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mSA.updateAllStudentsCoolness( false );
        }
    }
}