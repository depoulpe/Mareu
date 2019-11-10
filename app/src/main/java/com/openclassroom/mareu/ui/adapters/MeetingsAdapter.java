package com.openclassroom.mareu.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassroom.mareu.R;
import com.openclassroom.mareu.model.Meeting;
import com.openclassroom.mareu.ui.fragments.MeetingListFragment;

import java.text.SimpleDateFormat;
import java.util.List;

public class MeetingsAdapter extends RecyclerView.Adapter<MeetingsAdapter.ViewHolder> {

    private List<Meeting> mMeetings;
    private final MeetingListFragment.MeetingListCallback mListener;
    //Constructor
    public MeetingsAdapter(List<Meeting> mMeetings, MeetingListFragment.MeetingListCallback mListener){
        this.mMeetings = mMeetings;
        this.mListener=mListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView room,date, topic,participants;
        public ImageView circle;
        public ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.item_meeting_date);
            topic = (TextView) itemView.findViewById(R.id.item_meeting_subject);
            participants =  (TextView) itemView.findViewById(R.id.item_list_participants);
            circle = (ImageView) itemView.findViewById(R.id.item_list_color);
            deleteButton = (ImageButton) itemView.findViewById(R.id.item_list_delete_button);
        }
    }

    @NonNull
    @Override
    public MeetingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_meeting_item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Meeting meeting = mMeetings.get(position);
        String date  = new SimpleDateFormat(" MM/dd HH:mm").format(meeting.getStartDate());
        String[] rooms = holder.itemView.getResources().getStringArray(R.array.rooms_array);
        String[] colors = holder.itemView.getResources().getStringArray(R.array.rooms_color_array);

        holder.topic.setText(meeting.getTopic());
        holder.date.setText(" - "+date+" - "+rooms[meeting.getRoom()]);
        String participants= TextUtils.join(";",meeting.getParticipants());
        holder.participants.setText(participants);
        holder.circle.setColorFilter(Color.parseColor(colors[meeting.getRoom()]));
        holder.itemView.setOnClickListener(new View.OnClickListener(){
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
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDeleteClicked(meeting);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mMeetings.size());
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
