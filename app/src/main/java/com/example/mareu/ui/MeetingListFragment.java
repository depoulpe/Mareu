package com.example.mareu.ui;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MeetingListRecyclerViewAdapter mAdapter;
    private List<Meeting> meetingsList = new ArrayList<>();

    MeetingListCallback meetingListCallback;

    public interface MeetingListCallback{
        void onDeleteClicked(Meeting meeting);
        void onItemClicked(Meeting meeting);
    }

    public MeetingListFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(){
        MeetingListFragment fragment=new MeetingListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_meeting_list, container, false);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.fragment_meeting_recycler_view);
        this.configureRecyclerView();
        this.initMeetingList();
        return view;
    }

    private void configureRecyclerView() {
        this.meetingsList = new ArrayList<>();
        this.mAdapter = new MeetingListRecyclerViewAdapter(this.meetingsList,this.meetingListCallback);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initMeetingList() {
        Meeting meeting = new Meeting("Réunion A",new Timestamp(1571563782),45,"Peach");
        meetingsList.add(meeting);
        meeting = new Meeting("Réunion B",new Timestamp(1571564782),60,"Mario");
        meetingsList.add(meeting);
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity instanceof MeetingListCallback)
            meetingListCallback = (MeetingListCallback) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        meetingListCallback = null;
    }

}
