package com.openclassroom.mareu.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassroom.mareu.R;
import com.openclassroom.mareu.model.Meeting;
import com.openclassroom.mareu.ui.fragments.MeetingListFragment;

import java.text.SimpleDateFormat;
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
        String date  = new SimpleDateFormat(" MM/dd HH:mm").format(meeting.getStartDate());
        String[] rooms = meetingViewHolder.itemView.getResources().getStringArray(R.array.rooms_array);
        String[] colors = meetingViewHolder.itemView.getResources().getStringArray(R.array.rooms_color_array);

        meetingViewHolder.topic.setText(meeting.getTopic()+" - "+date+" - "+rooms[meeting.getRoom()]);
        String participants= TextUtils.join(";",meeting.getParticipants());
        meetingViewHolder.participants.setText(participants);
        meetingViewHolder.selectMeeting(meeting);
        meetingViewHolder.circle.setColorFilter(Color.parseColor(colors[meeting.getRoom()]));
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
