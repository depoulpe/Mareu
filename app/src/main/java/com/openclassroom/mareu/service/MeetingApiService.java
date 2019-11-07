package com.openclassroom.mareu.service;

import com.openclassroom.mareu.model.Meeting;

import java.util.Date;
import java.util.List;

public interface MeetingApiService {
    /**
     * Get all Meetings
     * @return {@link List}
     */
    List <Meeting> getMeetings();

    /**
     * Get all Meetings order by meeting room
     * @return {@link List}
     */
    List <Meeting> getMeetingsOrderByRoom(List<Meeting> meetings);

    /**
     * Get all Meetings order by meeting date
     * @return {@link List}
     * @param meetings
     */
    List <Meeting> getMeetingsOrderByDate(List<Meeting> meetings);

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

    List<Meeting> getMeetingsFilteredByDate(Date date);

    List<Meeting> getMeetingsFilteredByRoom(int room);
}
