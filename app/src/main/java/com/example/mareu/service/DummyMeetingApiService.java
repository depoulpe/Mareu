package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    /**
     * Get all Meetings
     *
     * @return {@link List}
     */
    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * Deletes a meeting
     *
     * @param meeting
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * Add a meeting
     *
     * @param meeting
     */
    @Override
    public void addMeeting(Meeting meeting) {
        //meeting
    }
}
