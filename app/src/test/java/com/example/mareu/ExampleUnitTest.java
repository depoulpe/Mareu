package com.example.mareu;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.DummyMeetingGenerator;
import com.example.mareu.service.MeetingApiService;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test on Meeting service
 */
@RunWith(JUnit4.class)
public class    ExampleUnitTest {
    private  MeetingApiService service;
   // private  List<Meeting> meetings;
    public List<String> participants = new ArrayList<String>() {
        {
            add("francis@lamzone.com");
            add("alexandra@lamzone.com");
            add("nico@lamzone.com");
        }
    };
    List <Meeting> meetings = Arrays.asList(
            new Meeting("M1",new Date(2019,10,22,8,15),new Date(2019,10,22,9,00),"Salle 1",participants),
            new Meeting("M2",new Date(2019,10,23,9,15),new Date(2019,10,23,10,00),"Salle 2",participants),
            new Meeting("M3",new Date(2019,10,23,10,15),new Date(2019,10,23,11,00),"Salle 1",participants)
    );
    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
        meetings = service.getMeetings();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }
    @Test
    public void addMeetingWithSuccess() {
        Meeting meeting=new Meeting("Reunion de test",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis() + 3600 * 1000),"Salle 1",participants);
        service.addMeeting(meeting);
        assert(service.getMeetings().contains(meeting));
    }

    @Test
    public void deleteMeetingWithSuccess(){
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void filterMeetingByDateWithSuccess(){


        Date date = new Date(2019,10,23,00,00);
        List<Meeting>filteredMeeting = service.getMeetingsFilteredByDate(date);
        // check if meetings only contains required date

    }

    @Test
    public void filterMeetingByRoomWithSuccess(){
        List <Meeting> meetings = Arrays.asList(
                new Meeting("M1",new Date(2019,10,22,8,15),new Date(2019,10,22,9,00),"Salle 1",participants),
                new Meeting("M2",new Date(2019,10,23,9,15),new Date(2019,10,23,10,00),"Salle 2",participants),
                new Meeting("M3",new Date(2019,10,23,10,15),new Date(2019,10,23,11,00),"Salle 1",participants)
        );


        String room ="Salle 1";
        List<Meeting>filteredMeeting = service.getMeetingsFilteredByRoom(room);
        // check if meetings only contains required room
        for (Meeting meeting : filteredMeeting)
        {
            assertTrue(meeting.getRoom().contains("Salle 1"));
        }

    }
    /**
public void sortMeetingByDateWithSuccess(){
    List <Meeting> meetings = Arrays.asList(
            new Meeting("M1",new Date(2019,10,23,8,15),new Date(2019,10,23,9,00),"Mario","part1@lamzone.com"),
            new Meeting("M2",new Date(2019,10,23,9,15),new Date(2019,10,23,10,00),"Peach","part1@lamzone.com"),
            new Meeting("M3",new Date(2019,10,23,10,15),new Date(2019,10,23,11,00),"Bowser","part1@lamzone.com")
    );
    List<Meeting>sorted = service.getMeetingsOrderByDate(meetings);
    List<Meeting>expected = Arrays.asList(
            new Meeting("M1",new Date(2019,10,23,8,15),new Date(2019,10,23,9,00),"Mario","part1@lamzone.com"),
            new Meeting("M2",new Date(2019,10,23,9,15),new Date(2019,10,23,10,00),"Peach","part1@lamzone.com"),
            new Meeting("M3",new Date(2019,10,23,10,15),new Date(2019,10,23,11,00),"Bowser","part1@lamzone.com")
    );

    //   assertTrue(sorted.equals(expected));
    assertArrayEquals(sorted.toArray(), expected.toArray());
    //Assert.assertArrayEquals(expected,sorted);

}
    public void sortMeetingByRoomWithSuccess(){
        List <Meeting> meetings = Arrays.asList(
                new Meeting("M1",new Date(2019,10,23,8,15),new Date(2019,10,23,9,00),"Mario","part1@lamzone.com"),
                new Meeting("M2",new Date(2019,10,23,9,15),new Date(2019,10,23,10,00),"Peach","part1@lamzone.com"),
                new Meeting("M3",new Date(2019,10,23,10,15),new Date(2019,10,23,11,00),"Bowser","part1@lamzone.com")
        );
        List<Meeting>sorted = service.getMeetingsOrderByRoom(meetings);
        List<Meeting>expected = Arrays.asList(
                new Meeting("M3",new Date(2019,10,23,10,15),new Date(2019,10,23,11,00),"Bowser","part1@lamzone.com"),
                new Meeting("M1",new Date(2019,10,23,8,15),new Date(2019,10,23,9,00),"Mario","part1@lamzone.com"),
                new Meeting("M2",new Date(2019,10,23,9,15),new Date(2019,10,23,10,00),"Peach","part1@lamzone.com")

        );
        assertArrayEquals(sorted.toArray(), expected.toArray());
    }*/
}