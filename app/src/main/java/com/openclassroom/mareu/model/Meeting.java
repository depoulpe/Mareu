package com.openclassroom.mareu.model;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Meeting {
    /** Identifier */
    private int id;

    /** Meeting room id */
    private int mRoom;

    /** Meeting date */
    private Date mDate;

    /** Meeting duration */
    private Date endDate;

    /** topic of the meeting */
    private String mTopic;

    /** List of participants */
    private List<String> mParticipants;
    /**
     * Constructor
     * @param room
     * @param startDate
     * @param endDate
     * @param mTopic
     */
    public Meeting(String mTopic, Date startDate, Date endDate, int room,List<String> participants){
        this.mRoom=room;
        this.mDate=startDate;
        this.endDate=endDate;
        this.mTopic = mTopic;
        setParticipants(participants);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom() {
        return mRoom;
    }

    public void setRoom(int room) {
        this.mRoom = room;
    }

    public Date getStartDate() {
        return mDate;
    }

    public void setStart_date(Date start_date) {mDate=start_date;}

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTopic() {
        return mTopic;
    }

    public void setTopic(String topic) {
        this.mTopic = topic;
    }

    public List<String> getParticipants() {
        return mParticipants;
    }

    public void setParticipants(List<String> participants) {
        this.mParticipants = participants;
    }

    public static Comparator<Meeting> MeetingRoomComparator = new Comparator<Meeting>() {
        @Override
        public int compare(Meeting o1, Meeting o2) {
            int room1 = o1.getRoom();
            int room2 = o2.getRoom();
            //ascending order
            return room2 - room1;
        }
    };

    public static Comparator<Meeting> MeetingDateComparator = new Comparator<Meeting>() {
        @Override
        public int compare(Meeting o1, Meeting o2) {
            Date ts1 = o1.getStartDate();
            Date ts2 = o2.getStartDate();
            //ascending order
            return ts1.compareTo(ts2);
        }
    };
}
