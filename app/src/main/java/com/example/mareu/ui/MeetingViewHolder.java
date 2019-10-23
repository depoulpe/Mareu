package com.example.mareu.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;

class MeetingViewHolder extends RecyclerView.ViewHolder {
    public TextView room,date,subject;
    public ImageView circle;
    public ImageButton deleteButton;
    private Meeting meeting;
    MeetingListFragment.MeetingListCallback mListener;

    public MeetingViewHolder(@NonNull View itemView, MeetingListFragment.MeetingListCallback _mListener) {
        super(itemView);
        mListener = _mListener;
        room = (TextView) itemView.findViewById(R.id.item_meeting_room);
        date = (TextView) itemView.findViewById(R.id.item_meeting_date);
        subject = (TextView) itemView.findViewById(R.id.item_meeting_subject);
        circle = (ImageView) itemView.findViewById(R.id.item_list_color);
        deleteButton = (ImageButton) itemView.findViewById(R.id.item_list_delete_button);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Ici on envoie un message à notre activity
                mListener.onItemClicked(meeting);

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Ici on envoie un message à notre activity
                mListener.onDeleteClicked(meeting);

            }
        });

    }
    public void selectMeeting(Meeting meeting)
    {
        this.meeting=meeting;
    }
}
