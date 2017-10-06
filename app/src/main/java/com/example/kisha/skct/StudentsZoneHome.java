package com.example.kisha.skct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StudentsZoneHome extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_zone_home);

        Button b2 = (Button) findViewById(R.id.button46);
        Button b3 = (Button) findViewById(R.id.button61);

        b2.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), GlobeKing.class);
                                startActivity(in);
                            }
                        }
                );
        b3.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                    startActivity(new Intent(StudentsZoneHome.this, InternalMarks.class));
                            }
                        }
                );

    }
}
