package com.example.mareu;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Intent meetingActivity = new Intent(view.getContext(), MeetingActivity.class);

             //   startActivity(meetingActivity);
                //  Snackbar.make(view, "Here's a Snackbar Notification", Snackbar.LENGTH_LONG)
                //    .setAction("Action", null).show();
            }

        });
    }
}
