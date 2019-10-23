package com.example.mareu.model;

import java.sql.Timestamp;
import java.util.List;

public class Meeting {


    private String room;
    private Timestamp start_date;
    private int duree;
    private String subject;
    private List<String> participants;

    public Meeting(String room,Timestamp start_date,int duree,String subject){
        setRoom(room);
        setStart_date(start_date);
        setDuree(duree);
        setSubject(subject);
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }
}
