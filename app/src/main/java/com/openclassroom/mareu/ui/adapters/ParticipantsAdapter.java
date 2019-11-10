package com.openclassroom.mareu.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.openclassroom.mareu.R;
import java.util.List;

public class ParticipantsAdapter extends RecyclerView.Adapter<ParticipantsAdapter.ViewHolder> {

    private List<String> mParticipants;

    public ParticipantsAdapter(List<String> participants) {
        this.mParticipants = participants;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mail;
        public ImageButton deleteButton;
        private List<String> mParticipants;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mail = (TextView) itemView.findViewById(R.id.item_participant_email);
            deleteButton = (ImageButton) itemView.findViewById(R.id.item_participant_delete_button);
        }
    }

    @Override
    public ParticipantsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_participant_item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String mail = mParticipants.get(position);
        holder.mail.setText(mail);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mParticipants.remove(position);
                notifyItemRemoved(position);
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
