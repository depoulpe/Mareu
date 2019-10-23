package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {
    /**
     * Get all Meetings
     * @return {@link List}
     */
    List <Meeting> getMeetings();

    /**
     * Deletes a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Add a meeting
     * @param meeting
     */
    void addMeeting(Meeting meeting);
}
