package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {
    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(1,"Réunion A",new Timestamp(1571563782),45,"Peach"),
            new Meeting(2,"Réunion C",new Timestamp(1571563782),45,"Peach"),
            new Meeting(3,"Réunion B",new Timestamp(1571563782),45,"Peach"),
            new Meeting(4,"Réunion D",new Timestamp(1571563782),45,"Peach"),
            new Meeting(5,"Réunion E",new Timestamp(1571563782),45,"Peach"),
            new Meeting(6,"Réunion F",new Timestamp(1571563782),45,"Peach"),
            new Meeting(7,"Réunion G",new Timestamp(1571563782),45,"Peach"),
            new Meeting(8,"Réunion H",new Timestamp(1571563782),45,"Peach"),
            new Meeting(9,"Réunion A",new Timestamp(1571563782),45,"Peach"),
            new Meeting(10,"Réunion I",new Timestamp(1571563782),45,"Peach"),
            new Meeting(11,"Réunion J",new Timestamp(1571563782),45,"Peach"),
            new Meeting(12,"Réunion B",new Timestamp(1571563782),45,"Peach"),
            new Meeting(13,"Réunion A",new Timestamp(1571563782),45,"Peach"),
            new Meeting(14,"Réunion C",new Timestamp(1571563782),45,"Peach")
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}
