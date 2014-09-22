package com.example.ADBTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        StudentsAdapter sa = new StudentsAdapter( this );
        sa.insertStudent( 1, "John", false );
        sa.insertStudent( 2, "Mary", true );
        sa.close();

    }

    public void buttonClicked( View view ) {
        Intent intent = new Intent(this, StudentsActivity.class);
        startActivity( intent );
    }
    public void buttonClickedSettings( View view ) {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity( intent );
    }

}
