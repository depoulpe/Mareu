package com.openclassroom.mareu.ui.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassroom.mareu.R;
import com.openclassroom.mareu.di.DI;
import com.openclassroom.mareu.model.Meeting;
import com.openclassroom.mareu.service.MeetingApiService;
import com.openclassroom.mareu.ui.adapters.MeetingsAdapter;
import com.openclassroom.mareu.utils.Utils;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MeetingsAdapter mAdapter;
    private List<Meeting> mMeetings;
    private MeetingApiService mApiService;
    MeetingListCallback meetingListCallback;
/*
    public void filterByRooms(boolean[] checkedItems) {
    }
*/

    public interface MeetingListCallback{
        void onDeleteClicked(Meeting meeting);
        void onItemClicked(Meeting meeting);
    }
/*
    public MeetingListFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(){
        MeetingListFragment fragment=new MeetingListFragment();
        return fragment;
    }
*/
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mApiService = DI.getMeetingApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_meeting_list, container, false);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.meetings_list);
        this.initMeetingList();
        this.configureRecyclerView();

        return view;
    }

    private void configureRecyclerView() {
        this.mAdapter = new MeetingsAdapter(this.mMeetings,this.meetingListCallback);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
   }

    private void initMeetingList() {
        mMeetings = mApiService.getMeetings();
        if(mAdapter!=null)
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
    public boolean sortBy(int itemId) {
        boolean ret=false;
        switch (itemId) {
            case R.id.action_filter_date:
                mMeetings = mApiService.getMeetingsOrderByDate(mMeetings);
                ret=true;
                break;
            case R.id.action_filter_room:
                mMeetings = mApiService.getMeetingsOrderByRoom(mMeetings);
                ret=true;
                break;
            case R.id.action_no_filter:
                mMeetings = mApiService.getMeetings();
                ret=true;
                break;
        }
        if(mAdapter!=null)
            mAdapter.notifyDataSetChanged();
        return ret;
    }

    public void deleteMeeting(final Meeting meeting) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.confirm_delete_desc);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mApiService.deleteMeeting(meeting);
                // remove filter
                noFilter();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }

    /**
     * Filter meetings by date
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     */
    public void filterByDate(int year, int monthOfYear, int dayOfMonth) {
        mMeetings = mApiService.getMeetingsFilteredByDate(Utils.getDate(year,monthOfYear,dayOfMonth,0,0));
        mAdapter.filterList(this.mMeetings );
    }

    /**
     * Remove filters
     */
    public void noFilter() {
        mMeetings = mApiService.getMeetings();
        mAdapter.filterList(this.mMeetings);
    }

    /**
     * Filter meetings by room
     * @param room
     */
    public void filterByRoom(int room) {
        this.mMeetings = mApiService.getMeetingsFilteredByRoom(room);
        mAdapter.filterList(this.mMeetings );
    }
    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
}
