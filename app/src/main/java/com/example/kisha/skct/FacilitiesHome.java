package com.example.kisha.skct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FacilitiesHome extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_home);

        Button b1,b2,b3,b4,b5;

        b1 = (Button) findViewById(R.id.button12);
        b2 = (Button) findViewById(R.id.button13);
        b3 = (Button) findViewById(R.id.button14);
        b4 = (Button) findViewById(R.id.button15);
        b5 = (Button) findViewById(R.id.button16);

        b1.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), LibraryHome.class);
                                startActivity(in);
                            }
                        }
                );
        b2.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), SmartBoard.class);
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
                                Intent in = new Intent(getApplicationContext(), Gym.class);
                                startActivity(in);
                            }
                        }
                );
        b4.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), Auditorium.class);
                                startActivity(in);
                            }
                        }
                );
        b5.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), FoodCourt.class);
                                startActivity(in);
                            }
                        }
                );
    }
}
