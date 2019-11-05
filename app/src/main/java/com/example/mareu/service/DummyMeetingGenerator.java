package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public abstract class DummyMeetingGenerator {
    public static List<String> participants = new ArrayList<String>() {
        {
            add("francis@lamzone.com");
            add("alexandra@lamzone.com");
            add("nico@lamzone.com");
        }
    };

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting("Réunion A",new Date(2019,10,22,8,15),new Date(2019,10,22,8,35),0,participants),
            new Meeting("Réunion B",new Date(2019,10,23,8,15),new Date(2019,10,23,8,55),1,participants),
            new Meeting("Réunion C",new Date(2019,10,23,14,00),new Date(2019,10,23,15,35),2,participants),
            new Meeting("Réunion D",new Date(2019,10,24,10,00),new Date(2019,10,24,10,55),0,participants)

            /* new Meeting("Réunion B",new Date(2019,10,23,9,00),new Date(2019,10,23,9,45),"Salle 1","franck@lamzone.com"),
            new Meeting("Réunion D",new Date(2019,10,24,16,00),new Date(2019,10,24,17,15),"Salle 2","alexandra@lamzone.com;michel@lamzone.com"),
            new Meeting("Réunion E",new Date(2019,10,24,9,15),new Date(2019,10,24,10,00),"Salle 3","carole@lamzone.com;gilles@lamzone.com")
           new Meeting(8,"Réunion H",new Date(2019,10,23,14,00),45,"Peach"),
            new Meeting(9,"Réunion I",new Date(2019,10,23,16,15),45,"Wario"),
            new Meeting(10,"Réunion J",new Date(2019,10,23,8,15),45,"Bowser"),
            new Meeting(11,"Réunion K",new Date(2019,10,23,8,15),45,"Yoshi"),
            new Meeting(12,"Réunion L",new Date(2019,10,23,8,15),45,"Kong"),
            new Meeting(13,"Réunion M",new Date(2019,10,23,8,15),45,"Maskass"),
            new Meeting(14,"Réunion N",new Date(2019,10,23,15,30),45,"Peach")*/
    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

}
