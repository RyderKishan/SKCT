package com.example.kisha.skct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Development extends AppCompatActivity
{
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_development);

        b1 = (Button) findViewById(R.id.button48);

        b1.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in3 = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(in3);
                            }
                        }
                );
    }
}
