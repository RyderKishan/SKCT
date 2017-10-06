package com.example.kisha.skct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

public class CalendarDateEdited extends AppCompatActivity
{
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_date_edited);

        calendarView = (CalendarView) findViewById(R.id.calendarView2);
        calendarView.setShowWeekNumber(false);


    }
}
