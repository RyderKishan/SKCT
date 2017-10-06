package com.example.kisha.skct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LaunchActivity extends AppCompatActivity
{
    Button next;
    TextView text;
    int count, increment = 1;
    String[] display = {
            "Hai",
            "Welcome to SKCT APP",
            "Try out the new SKCT Chat",
            "Submit your OD forms online!",
            "Your marks are in Students zone!","Upload your profile pic!","Stand out from others!","You are ready to rock!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launch);

        next = (Button) findViewById(R.id.dummy_button);
        text = (TextView) findViewById(R.id.full_text);

        count = display.length;

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (increment >= count)
                {
                    startActivity(new Intent(getApplicationContext(), LoginHome.class));
                }
                else
                    changeText();
            }
        });


    }

    void changeText()
    {
        text.setText(display[increment]);
        if (increment == count-1)
        {
            next.setText("Login now!");
        }
        increment++;
    }
}
