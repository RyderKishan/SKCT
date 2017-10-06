package com.example.kisha.skct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FormsHome extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_home);

        Button b1 = (Button) findViewById(R.id.button20);
        Button b2 = (Button) findViewById(R.id.button21);
        Button b3 = (Button) findViewById(R.id.button55);

        b1.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in3 = new Intent(getApplicationContext(), Development.class);
                                startActivity(in3);
                            }
                        }
                );
        b2.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in3 = new Intent(getApplicationContext(), LeaveFormEdited.class);
                                startActivity(in3);
                            }
                        }
                );
        b3.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in3 = new Intent(getApplicationContext(), OdCheck.class);
                                startActivity(in3);
                            }
                        }
                );

    }
}
