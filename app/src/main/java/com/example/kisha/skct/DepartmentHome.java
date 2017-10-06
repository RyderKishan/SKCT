package com.example.kisha.skct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DepartmentHome extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_home);

        Button b1 = (Button) findViewById(R.id.button);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);
        Button b4 = (Button) findViewById(R.id.button4);
        Button b5 = (Button) findViewById(R.id.button5);
        Button b6 = (Button) findViewById(R.id.button6);
        Button b7 = (Button) findViewById(R.id.button7);
        Button b8 = (Button) findViewById(R.id.button8);

        b1.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), MechHome.class);
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
                                Intent in = new Intent(getApplicationContext(), CivilHome.class);
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
                                Intent in = new Intent(getApplicationContext(), EceHome.class);
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
                                Intent in = new Intent(getApplicationContext(), EeeHome.class);
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
                                Intent in = new Intent(getApplicationContext(), CseHome.class);
                                startActivity(in);
                            }
                        }
                );
        b6.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), ItHome.class);
                                startActivity(in);
                            }
                        }
                );
        b7.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), IceHome.class);
                                startActivity(in);
                            }
                        }
                );
        b8.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), ShHome.class);
                                startActivity(in);
                            }
                        }
                );


    }
}
