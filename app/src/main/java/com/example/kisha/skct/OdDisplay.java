package com.example.kisha.skct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import static com.example.kisha.skct.MainActivity.auth;
import static com.example.kisha.skct.MainActivity.userName;


public class OdDisplay extends AppCompatActivity
{
    Button button, button2;
    String reference;
    TextView textView,textView2;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_od_display);

        textView = (TextView) findViewById(R.id.textView137);
        textView2 = (TextView) findViewById(R.id.textView135);
        user = auth.getCurrentUser();
        reference = getIntent().getStringExtra("REFERENCE_ID");
        textView2.setText(userName);
        textView.setText(reference);

        button = (Button) findViewById(R.id.button49);
        button2 = (Button) findViewById(R.id.button45);

        button.setOnClickListener
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

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent in3 = new Intent(getApplicationContext(), FormsHome.class);
        startActivity(in3);
    }
}
