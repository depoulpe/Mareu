package com.openclassroom.mareu.service;

import com.openclassroom.mareu.model.Meeting;
import com.openclassroom.mareu.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public abstract class DummyMeetingGenerator {
    public static List<String> participants = new ArrayList<String>() {
        {
            add("francis@lamzone.com");
            add("alexandra@lamzone.com");
            add("nico@lamzone.com");
        }
    };
    public Calendar  mCalendarStart;

    //.set(mYear, mMonth, mDay, hourOfDay, minute)
    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Soutenance P4", Utils.getDate(2019,10,13,13,00),Utils.getDate(2019,10,13,13,35),0,participants),
            new Meeting("Réunion B",Utils.getDate(2019,10,23,8,15),Utils.getDate(2019,10,23,8,55),1,participants),
            new Meeting("Réunion C",Utils.getDate(2019,10,23,14,00),Utils.getDate(2019,10,23,15,35),2,participants),
            new Meeting("Réunion D",Utils.getDate(2019,10,24,10,00),Utils.getDate(2019,10,24,10,55),0,participants)
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}
