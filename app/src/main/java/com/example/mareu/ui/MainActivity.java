package com.example.mareu.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;

public class MainActivity extends AppCompatActivity implements MeetingListFragment.MeetingListCallback{
    private MeetingListFragment meetingListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Intent meetingActivity = new Intent(view.getContext(), MeetingActivity.class);

             //   startActivity(meetingActivity);
                //  Snackbar.make(view, "Here's a Snackbar Notification", Snackbar.LENGTH_LONG)
                //    .setAction("Action", null).show();
            }

        });
        this.configureAndShowMeetingFragment();
    }
    /**
     * CONFIGURATION
     */

    private void configureAndShowMeetingFragment(){

        meetingListFragment = (MeetingListFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);

        if (meetingListFragment == null) {
            meetingListFragment = new MeetingListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_frame_layout, meetingListFragment)
                    .commit();
        }
    }

    @Override
    public void onDeleteClicked(Meeting meeting) {
        Toast.makeText(this,  meeting.getSubject()+"deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(Meeting meeting) {
        Toast.makeText(this,  meeting.getSubject(), Toast.LENGTH_SHORT).show();
    }


}
