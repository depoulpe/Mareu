package com.example.mareu.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mareu.R;

import java.util.List;

class ParticipantsViewHolder extends RecyclerView.ViewHolder{
    public TextView mail;
    public ImageButton deleteButton;
    private List<String> mParticipants;

    public ParticipantsViewHolder(@NonNull View itemView) {
        super(itemView);
        mail = (TextView) itemView.findViewById(R.id.item_participant_email);
        deleteButton = (ImageButton) itemView.findViewById(R.id.item_participant_delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Ici on envoie un message à notre activity
           //     mListener.onDeleteClicked(mMeeting);

            }
        });
    }
}
