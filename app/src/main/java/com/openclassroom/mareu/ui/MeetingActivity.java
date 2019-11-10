package com.openclassroom.mareu.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.openclassroom.mareu.R;
import com.openclassroom.mareu.ui.fragments.MeetingFragment;

public class MeetingActivity extends AppCompatActivity {
    private MeetingFragment mMeetingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        configureAndShowMeetingFragment();
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
}
