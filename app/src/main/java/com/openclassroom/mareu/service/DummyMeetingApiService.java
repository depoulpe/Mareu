package com.openclassroom.mareu.service;

import com.openclassroom.mareu.model.Meeting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> mMeetings = DummyMeetingGenerator.generateMeetings();

    /**
     * Get all Meetings
     *
     * @return {@link List}
     */
    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }


    /**
     * Deletes a meeting
     *
     * @param meeting
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    /**
     * Add a meeting
     *
     * @param meeting
     */
    @Override
    public void addMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    /**
     * Get all Meetings filtered by meeting room
     * @param date
     * @return {@link List}
     */
    @Override
    public List<Meeting> getMeetingsFilteredByDate(Date date) {
        List<Meeting> meetings = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String meeting_date;
        String filterDate = sdf.format(date);
        for(Meeting meeting : mMeetings)
        {
            meeting_date =  sdf.format(meeting.getStartDate());
           /// if(DateUtils.)
            if( meeting_date.compareTo(filterDate)==0)
            {
                meetings.add(meeting);
            }
        }
        return meetings;
    }

    /**
     * Get all Meetings filtered by meeting room
     * @param room
     * @return {@link List}
     */
    @Override
    public List<Meeting> getMeetingsFilteredByRoom(int room) {
        List<Meeting> meetings = new ArrayList<>();

        for(Meeting meeting : mMeetings)
        {
            if(meeting.getRoom() == room)
            {
                meetings.add(meeting);
            }
        }
        return meetings;
    }

    /**
     * Get all Meetings order by meeting room
     *
     * @return {@link List}
     */
    @Override
    public List<Meeting> getMeetingsOrderByRoom( List<Meeting> meetings) {
        Collections.sort(meetings, Meeting.MeetingRoomComparator);
        mMeetings=meetings;
        return meetings;
    }

    /**
     * Get all Meetings order by meeting date
     *
     * @return {@link List}
     * @param meetings
     */
    @Override
    public List<Meeting> getMeetingsOrderByDate(List<Meeting> meetings) {
        Collections.sort(meetings, Meeting.MeetingDateComparator);
        mMeetings=meetings;
        return meetings;
    }

}
