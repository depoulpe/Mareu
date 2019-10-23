package com.example.mareu.model;

import java.sql.Timestamp;
import java.util.List;

public class Meeting {
    /** Identifier */
    private int id;

    /** Meeting room name */
    private String room;

    /** Meeting date */
    private Timestamp startDate;

    /** Meeting duration */
    private int duration;

    /** Subject of meeting */
    private String subject;

    /** List of participants */
    private List<String> participants;
    /**
     * Constructor
     * @param id
     * @param room
     * @param startDate
     * @param duration
     * @param subject
     */
    public Meeting(int id,String room,Timestamp startDate,int duration,String subject){
        this.id = id;
        this.room=room;
        this.startDate=startDate;
        this.duration=duration;
        this.subject=subject;/*
        setId(id);
        setRoom(room);
        setStart_date(start_date);
        setDuree(duree);
        setSubject(subject);*/
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStart_date(Timestamp start_date) {
        this.startDate = startDate;
    }

    public int getDuree() {
        return duration;
    }

    public void setDuree(int duree) {
        this.duration = duree;
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
