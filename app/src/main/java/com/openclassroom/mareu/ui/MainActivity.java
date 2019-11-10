package com.openclassroom.mareu.ui;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.openclassroom.mareu.R;
import com.openclassroom.mareu.model.Meeting;
import com.openclassroom.mareu.ui.fragments.MeetingFragment;
import com.openclassroom.mareu.ui.fragments.MeetingListFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements MeetingListFragment.MeetingListCallback{
    private MeetingListFragment meetingListFragment;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureAndShowMeetingListFragment();
        this.configureFab();
    }

    private void configureFab() {


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean is_tablet = getResources().getBoolean(R.bool.is_tablet);
                if (is_tablet)
                {
                    DialogFragment dialogFragment = new MeetingFragment();
                    dialogFragment.show(getSupportFragmentManager(),"MeetingFragment");

                }
                else
                {
                    Intent meetingActivity = new Intent(view.getContext(), MeetingActivity.class);
                    startActivity(meetingActivity);
                }
            }

        });

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
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.choose_room);// add a radio button list
                String[] rooms = getResources().getStringArray(R.array.rooms_array);
                final int[] checkedItem = {0}; // cow
                builder.setSingleChoiceItems(rooms, checkedItem[0], new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkedItem[0] = which;
                    }
                });// add OK and Cancel buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        meetingListFragment.filterByRoom(checkedItem[0]);
                    }
                });
                builder.setNegativeButton(R.string.cancel, null);// create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.action_no_filter:
                meetingListFragment.noFilter();
                break;
        }
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

    @Override
    public void onDeleteClicked(Meeting meeting) {
        meetingListFragment.deleteMeeting(meeting);
    }

    @Override
    public void onItemClicked(Meeting meeting) {
        Toast.makeText(this,  meeting.getTopic(), Toast.LENGTH_SHORT).show();
    }
}