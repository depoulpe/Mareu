package com.example.mareu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

class ParticipantRecyclerViewAdapter extends RecyclerView.Adapter<ParticipantsViewHolder> {

    private List<String> mParticipants;
    public ParticipantRecyclerViewAdapter(List<String> participants) {
        this.mParticipants = participants;
    }

    @NonNull
    @Override
    public ParticipantsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_participant_item,viewGroup,false);
        return new ParticipantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantsViewHolder participantsViewHolder, final int position) {
        String mail = mParticipants.get(position);
        participantsViewHolder.mail.setText(mail);
        participantsViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mParticipants.remove(position);
                notifyItemRangeChanged(position,mParticipants.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mParticipants.size();
    }

    public List<String> getParticipants() {
        return this.mParticipants;
    }
}
