package com.openclassroom.mareu.ui.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.openclassroom.mareu.R;
import com.openclassroom.mareu.di.DI;
import com.openclassroom.mareu.model.Meeting;
import com.openclassroom.mareu.service.MeetingApiService;
import com.openclassroom.mareu.ui.adapters.ParticipantsAdapter;
import com.openclassroom.mareu.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.view.Window.FEATURE_NO_TITLE;
import static com.openclassroom.mareu.utils.Utils.emailValidator;

public class MeetingFragment extends DialogFragment{
    private TextView mEditStartDate;
    private TextView mEditStartTime;
    private TextView mEditEndTime;
    private Button mAddParticipantButton;
    private Spinner mSpinner;
    private EditText mTopic;
    private EditText mGuestEmail;
    private Toolbar mToolbar;

    private int mYear, mMonth, mDay, mHour, mMinute;
    private SimpleDateFormat mDateFormat;
    private SimpleDateFormat mTimeFormat;
    private Calendar mCalendarStart;
    private Calendar mCalendarEnd;
    private MeetingApiService mMeetingApiService;
    private ParticipantsAdapter mParticipantAdapter;
    private RecyclerView mParticipantRecyclerView;
    private List<String> mParticipants;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meeting_add, container, false);

        mParticipantRecyclerView=(RecyclerView)view.findViewById(R.id.meetings_list);
        mMeetingApiService = DI.getMeetingApiService();
        mParticipants = new ArrayList<String>();

        configureRecyclerView();
        initCalendars();
        initUI(view);
        return view;
    }
    private void configureRecyclerView() {
        this.mParticipantAdapter = new ParticipantsAdapter(this.mParticipants);
        this.mParticipantRecyclerView.setAdapter(this.mParticipantAdapter);
        this.mParticipantRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
    /**
     * Ui part
     */
    private void initUI(View view) {
        mEditStartDate = (TextView) view.findViewById(R.id.startDate);
        mEditStartTime = (TextView) view.findViewById(R.id.startTime);
        mEditEndTime = (TextView) view.findViewById(R.id.endTime);
        mSpinner = (Spinner) view.findViewById(R.id.spinner);
        mTopic = ((EditText)view.findViewById(R.id.topic));
        mAddParticipantButton = (Button) view.findViewById(R.id.add_participant_button);
        mGuestEmail = ((EditText) view.findViewById(R.id.participant));
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);


        initToolbar();
        mTopic.setText("");
        initStartDate();
        initEndDate();
        initRooms();
        initParticipants();
    }

    private void initToolbar() {
        boolean is_tablet = getResources().getBoolean(R.bool.is_tablet);
        if (is_tablet) {
            getDialog().getWindow().requestFeature(FEATURE_NO_TITLE);
        }
        // inflate menu
        mToolbar.inflateMenu(R.menu.meeting_menu);
        mToolbar.setNavigationIcon(R.drawable.ic_close_24);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MeetingListFragment meetingListFragment = (MeetingListFragment) getFragmentManager().findFragmentById(R.id.activity_main_frame_layout);
                if (meetingListFragment == null) {
                    getActivity().finish();
                }
                else
                {
                    dismiss();
                }
            }
        });
        mToolbar.setTitle(R.string.add_meeting);
        mToolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.action_save) {

                            String topic= mTopic.getText().toString();
                            if(topic.length()==0)
                                topic=getText(R.string.no_title).toString();
                            Meeting meeting = new Meeting(topic,mCalendarStart.getTime(),mCalendarEnd.getTime(),mSpinner.getSelectedItemPosition(),mParticipantAdapter.getParticipants());
                            mMeetingApiService.addMeeting(meeting);

                            //if not tablet
                            MeetingListFragment meetingListFragment = (MeetingListFragment) getFragmentManager().findFragmentById(R.id.activity_main_frame_layout);

                            if (meetingListFragment == null) {
                                getActivity().finish();
                            }
                            else
                            {
                                dismiss();
                            }
                        }
                        return true;
                    }
                });
    }
    private void initStartDate() {
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
                                mEditStartTime.setText(mTimeFormat.format(Utils.getDate(mYear, mMonth, mDay, hourOfDay, minute)));
                                /**
                                 * Set meeting end to start time  +1 hour
                                 */
                                mEditEndTime.setText(mTimeFormat.format(Utils.getDate(mYear, mMonth, mDay, hourOfDay+1, minute)));
                            }
                        }, mHour, 0, true);
                timePickerDialog.show();
            }
        });
    }

    private void initEndDate() {
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
                                mEditEndTime.setText(mTimeFormat.format(Utils.getDate(mYear, mMonth, mDay, hourOfDay, minute)));
                            }
                        }, mHour+1, 0, true);
                timePickerDialog.show();
            }
        });
    }

    private void initRooms() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.rooms_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);
    }

    private void initParticipants() {
        mAddParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailValidator(mGuestEmail.getText().toString())) {
                    mParticipants.add(mGuestEmail.getText().toString());
                    if (mParticipantAdapter != null)
                        mParticipantAdapter.notifyDataSetChanged();
                    mGuestEmail.setText("");
                }
                else
                {
                    Toast.makeText(getContext(),  getText(R.string.invalid_email).toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
