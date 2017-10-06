package com.example.kisha.skct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlacementHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_home);

        Button b1,b2,b3,b4;
        b1 = (Button) findViewById(R.id.button9);
        b2 = (Button) findViewById(R.id.button10);
        b3 = (Button) findViewById(R.id.button11);
        b4 = (Button) findViewById(R.id.button39);

        b1.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), PlacementOfficer.class);
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
                                Intent in = new Intent(getApplicationContext(), Table2.class);
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
                                Intent in = new Intent(getApplicationContext(), Table.class);
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
                                Intent in = new Intent(getApplicationContext(), Recruiters.class);
                                startActivity(in);
                            }
                        }
                );
    }
}
