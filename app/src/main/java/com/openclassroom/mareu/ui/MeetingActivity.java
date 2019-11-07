package com.openclassroom.mareu.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.openclassroom.mareu.R;
import com.openclassroom.mareu.ui.fragments.MeetingFragment;

public class MeetingActivity extends AppCompatActivity {
    private MeetingFragment mMeetingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        configureAndShowMeetingFragment();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * CONFIGURATION
     */

    private void configureAndShowMeetingFragment(){

        mMeetingFragment = (MeetingFragment) getSupportFragmentManager().findFragmentById(R.id.activity_meeting_frame_layout);

        if (mMeetingFragment == null) {
            mMeetingFragment = new MeetingFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_meeting_frame_layout, mMeetingFragment)
                    .commit();
        }
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
           // case R.id.action_save:
           //     break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
