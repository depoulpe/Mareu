package com.example.mareu.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MeetingFragment extends Fragment{

    //    private OnFragmentInteractionListener mListener;
    private TextView mEditStartDate;
    private TextView mEditStartTime;
    private TextView mEditEndTime;
    private Date date;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private SimpleDateFormat mDateFormat;
    private SimpleDateFormat mTimeFormat;
    private Calendar mCalendarStart;
    private Calendar mCalendarEnd;
    private MeetingApiService mMeetingApiService;

    MeetingCallback meetingCallback;
    private ParticipantRecyclerViewAdapter mParticipantAdapter;
    private RecyclerView mParticipantRecyclerView;
    private List<String> mParticipants;

    public interface MeetingCallback{
        void onAddMeeting(Meeting meeting);
    }

    public MeetingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meeting, container, false);
        mParticipantRecyclerView=(RecyclerView)view.findViewById(R.id.fragment_meeting_recycler_view);
        mMeetingApiService = DI.getMeetingApiService();
        mParticipants = new ArrayList<String>();

        configureRecyclerView();
        initCalendars();
        initMeeting(view);
        initRooms(view);


        return view;
    }

    private void initRooms(View view) {
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.rooms_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    /**
     * Init start calendar to next hour
     */
    private void initCalendars() {
        mCalendarStart = Calendar.getInstance();
        mCalendarStart.add(Calendar.HOUR, 1);
        mCalendarStart.add(Calendar.MINUTE,0);
        mCalendarEnd = Calendar.getInstance();
        mCalendarEnd.add(Calendar.HOUR, 2);
        mCalendarEnd.add(Calendar.MINUTE,0);


        mYear = mCalendarStart.get(mCalendarStart.YEAR);
        mMonth = mCalendarStart.get(mCalendarStart.MONTH);
        mDay = mCalendarStart.get(mCalendarStart.DAY_OF_MONTH);
        mHour = mCalendarStart.get(mCalendarStart.HOUR);
        mMinute = mCalendarStart.get(mCalendarStart.MINUTE);


        mDateFormat = new SimpleDateFormat((String) getText(R.string.date_format));
        mTimeFormat = new SimpleDateFormat("HH:mm");
    }

    private void initMeeting(final View view) {
        mEditStartDate = (TextView) view.findViewById(R.id.startDate);
        mEditStartDate.setText(mDateFormat.format(mCalendarStart.getTime()));
        mEditStartDate.setClickable(true);
        mEditStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker datePicker, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                mCalendarStart.set(year, monthOfYear, dayOfMonth);
                                mEditStartDate.setText(mDateFormat.format(mCalendarStart.getTime()));
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        mEditStartTime = (TextView) view.findViewById(R.id.startTime);
        mEditStartTime.setText(mTimeFormat.format(mCalendarStart.getTime()));
        mEditStartTime.setClickable(true);
        mEditStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            /**
                             * Called when the user is done setting a new time and the dialog has
                             * closed.
                             *
                             * @param view      the view associated with this listener
                             * @param hourOfDay the hour that was set
                             * @param minute    the minute that was set
                             */
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                mCalendarStart.set(mYear, mMonth, mDay, hourOfDay, minute);
                                mEditStartTime.setText(mTimeFormat.format(mCalendarStart.getTime()));
                                /**
                                 * Set meeting end to start time  +1 hour
                                 */
                                mCalendarEnd.set(mYear, mMonth, mDay, hourOfDay+1, minute);
                                mEditEndTime.setText(mTimeFormat.format(mCalendarEnd.getTime()));
                            }
                        }, mHour, 0, true);
                timePickerDialog.show();
            }
        });

        mEditEndTime = (TextView) view.findViewById(R.id.endTime);
        mEditEndTime.setText(mTimeFormat.format(mCalendarEnd.getTime()));
        mEditEndTime.setClickable(true);
        mEditEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            /**
                             * Called when the user is done setting a new time and the dialog has
                             * closed.
                             *
                             * @param view      the view associated with this listener
                             * @param hourOfDay the hour that was set
                             * @param minute    the minute that was set
                             */
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                mCalendarEnd.set(mYear, mMonth, mDay, hourOfDay, minute);
                                mEditEndTime.setText(mTimeFormat.format(mCalendarEnd.getTime()));
                            }
                        }, mHour+1, 0, true);
                timePickerDialog.show();
            }
        });

        Button saveButton = (Button) view.findViewById(R.id.save_button);
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        //   mSaveButton.setEnabled(false);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic= ((EditText)view.findViewById(R.id.topic)).getText().toString();
                String guests =((EditText)view.findViewById(R.id.participant)).getText().toString();
                Meeting meeting = new Meeting(topic,mCalendarStart.getTime(),mCalendarEnd.getTime(),spinner.getSelectedItem().toString(),mParticipantAdapter.getParticipants());

                mMeetingApiService.addMeeting(meeting);
                getActivity().finish();
            }
        });

        /**
         *
         */

        Button addParticipantButton = (Button) view.findViewById(R.id.add_participant_button);
        addParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = ((EditText) view.findViewById(R.id.participant));
                mParticipants.add(email.getText().toString());
                if(mParticipantAdapter!=null)
                    mParticipantAdapter.notifyDataSetChanged();
                email.setText("");
            }
        });

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity instanceof MeetingFragment.MeetingCallback)
            meetingCallback = (MeetingFragment.MeetingCallback) activity;
    }

    private void configureRecyclerView() {
        this.mParticipantAdapter = new ParticipantRecyclerViewAdapter(this.mParticipants);
        this.mParticipantRecyclerView.setAdapter(this.mParticipantAdapter);
        this.mParticipantRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
