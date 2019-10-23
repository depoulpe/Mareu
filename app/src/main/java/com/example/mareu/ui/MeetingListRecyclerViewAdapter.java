package com.example.mareu.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;

import java.util.List;

public class MeetingListRecyclerViewAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    private final List<Meeting> meetings;
    private final MeetingListFragment.MeetingListCallback mListener;
    //Constructor
    public MeetingListRecyclerViewAdapter(List<Meeting> meetings, MeetingListFragment.MeetingListCallback mListener){
        this.meetings = meetings;
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
       final Meeting meeting = meetings.get(position);
        meetingViewHolder.room.setText(meeting.getRoom());
      //  meetingViewHolder.date.setText(meeting.getStart_date().toString());
        meetingViewHolder.subject.setText(meeting.getSubject());
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
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                // builder.setTitle(R.string.confirm_delete_caption);
                builder.setMessage(R.string.confirm_delete_desc);
                builder.setCancelable(false);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //  EventBus.getDefault().post(new RemoveNeighbourFromFavoriteEvent(neighbour));
                        //   EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                    }
                });

                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.meetings.size();
    }
}
