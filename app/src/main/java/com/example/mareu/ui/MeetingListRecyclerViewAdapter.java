package com.example.mareu.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MeetingListRecyclerViewAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    private List<Meeting> mMeetings;
    private final MeetingListFragment.MeetingListCallback mListener;
    //Constructor
    public MeetingListRecyclerViewAdapter(List<Meeting> mMeetings, MeetingListFragment.MeetingListCallback mListener){
        this.mMeetings = mMeetings;
        this.mListener=mListener;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_meeting_item,viewGroup,false);
        return new MeetingViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(final MeetingViewHolder meetingViewHolder, int position) {
       final Meeting meeting = mMeetings.get(position);
        //meetingViewHolder.room.setText(meeting.getRoom());
        String date  = new SimpleDateFormat(" MM/dd HH:mm").format(meeting.getStartDate());

        meetingViewHolder.topic.setText(meeting.getTopic()+" - "+date+" - "+meeting.getRoom());
        String participants= TextUtils.join(";",meeting.getParticipants());
        meetingViewHolder.participants.setText(participants);
        meetingViewHolder.selectMeeting(meeting);
;
/**
 * Dommage getForeground API 23

 Drawable background = meetingViewHolder.circle.getForeground();
 if (background instanceof ShapeDrawable) {
 // cast to 'ShapeDrawable'
 ShapeDrawable shapeDrawable = (ShapeDrawable) background;
 shapeDrawable.getPaint().setColor(ContextCompat.getColor(meetingViewHolder.itemView.getContext(), R.color.colorA));
 } else if (background instanceof GradientDrawable) {
 // cast to 'GradientDrawable'
 GradientDrawable gradientDrawable = (GradientDrawable) background;
 gradientDrawable.setColor(ContextCompat.getColor(meetingViewHolder.itemView.getContext(), R.color.colorA));
 } else if (background instanceof ColorDrawable) {
 // alpha value may need to be set again after this call
 ColorDrawable colorDrawable = (ColorDrawable) background;
 colorDrawable.setColor(ContextCompat.getColor(meetingViewHolder.itemView.getContext(), R.color.colorA));
 }
 */

      //  meetingViewHolder.date.setTextColor(Color.BLUE);
      //  meetingViewHolder.circle.setColorFilter(Color.BLUE);
       // meetingViewHolder.circle.getDrawable().setColorFilter(ColorFilter);

        meetingViewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                mListener.onItemClicked(meeting);
            }
        });
        meetingViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDeleteClicked(meeting);
                notifyItemRangeChanged(meetingViewHolder.getAdapterPosition(),mMeetings.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mMeetings.size();
    }

    public void filterList(List<Meeting> filteredList)
    {
        this.mMeetings = filteredList;
        notifyDataSetChanged();
    }
}
