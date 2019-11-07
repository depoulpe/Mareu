package com.openclassroom.mareu.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassroom.mareu.R;
import com.openclassroom.mareu.model.Meeting;
import com.openclassroom.mareu.ui.fragments.MeetingListFragment;

class MeetingViewHolder extends RecyclerView.ViewHolder {
    public TextView room,date, topic,participants;
    public ImageView circle;
    public ImageButton deleteButton;
    private Meeting mMeeting;
    MeetingListFragment.MeetingListCallback mListener;

    public MeetingViewHolder(@NonNull View itemView, MeetingListFragment.MeetingListCallback _mListener) {
        super(itemView);
        mListener = _mListener;
       // room = (TextView) itemView.findViewById(R.id.item_meeting_room);
      //  date = (TextView) itemView.findViewById(R.id.item_meeting_date);
        topic = (TextView) itemView.findViewById(R.id.item_meeting_subject);
        participants =  (TextView) itemView.findViewById(R.id.item_list_participants);
        circle = (ImageView) itemView.findViewById(R.id.item_list_color);
        deleteButton = (ImageButton) itemView.findViewById(R.id.item_list_delete_button);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Ici on envoie un message à notre activity
                mListener.onItemClicked(mMeeting);

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Ici on envoie un message à notre activity
                mListener.onDeleteClicked(mMeeting);

            }
        });

    }
    public void selectMeeting(Meeting meeting)
    {
        this.mMeeting =meeting;
    }
}
