package com.example.mareu.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.utils.SpinnerDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements MeetingListFragment.MeetingListCallback, MeetingFragment.MeetingCallback {
    private MeetingListFragment meetingListFragment;
    private FloatingActionButton fab;
    private MeetingFragment meetingFragment;
    private MeetingApiService mMeetingApiService;

    // private   Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeetingApiService = DI.getMeetingApiService();


        setContentView(R.layout.activity_main);
        configureToolbar();
        this.configureAndShowMeetingListFragment();
        this.configureAndShowMeetingFragment();
        if (meetingFragment == null) {
            this.configureFab();
        }
    }

    private void configureFab() {


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (meetingFragment == null || !meetingFragment.isVisible()) {
                    Intent meetingActivity = new Intent(view.getContext(), MeetingActivity.class);
                    startActivity(meetingActivity);
                }
              /*  Log.i("OCR","ca marche");
                  Snackbar.make(view, "Here's a Snackbar Notification", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/

            }

        });

    }

    private void configureToolbar() {
        //  toolbar = (Toolbar) findViewById(R.id.toolbar);
        //   setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        switch(item.getItemId())
        {
            case R.id.action_filter_date:
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                meetingListFragment.filterByDate(year, month, day);
                            }
                        }, year, month, dayOfMonth);

                datePickerDialog.show();
                break;
            case R.id.action_filter_room:
                SpinnerDialog mSpinnerDialog = new SpinnerDialog(this, new SpinnerDialog.DialogListener() {
                    public void cancelled() {
                        // do your code here
                    }
                    public void ready(int n) {

                        String room="Salle 1";
                        meetingListFragment.filterByRoom(room);
                    }

                });
                mSpinnerDialog.show();

                break;
            case R.id.action_no_filter:
                meetingListFragment.noFilter();
                break;
        }

      /*  ArrayList<String> mTimers =  mMeetingApiService.getMeetingsRoom//getResources().getStringArray(R.array.my_array);
        mTimers.set(0, "By Zip");


        if(meetingListFragment.sortBy(item.getItemId()))
            return true;
*/
        return super.onOptionsItemSelected(item);
    }
    /**
     * CONFIGURATION
     */

    private void configureAndShowMeetingListFragment(){

        meetingListFragment = (MeetingListFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);

        if (meetingListFragment == null) {
            meetingListFragment = new MeetingListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_frame_layout, meetingListFragment)
                    .commit();
        }
    }
    private void configureAndShowMeetingFragment(){
        meetingFragment = (MeetingFragment) getSupportFragmentManager().findFragmentById(R.id.activity_meeting_frame_layout);

        //A - We only add DetailFragment in Tablet mode (If found frame_layout_detail)
        if (meetingFragment == null && findViewById(R.id.activity_meeting_frame_layout) != null) {
            meetingFragment = new MeetingFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_meeting_frame_layout, meetingFragment)
                    .commit();
        }
    }
    @Override
    public void onDeleteClicked(Meeting meeting) {
        meetingListFragment.deleteMeeting(meeting);
    }

    @Override
    public void onItemClicked(Meeting meeting) {
        Toast.makeText(this,  meeting.getTopic(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddMeeting(Meeting meeting) {
        meetingListFragment.addMeeting(meeting);
    }
}
