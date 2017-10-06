package com.example.kisha.skct;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admission extends AppCompatActivity
{

    Button b1,b2,b3,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission);

        b1 = (Button) findViewById(R.id.button50);
        b2 = (Button) findViewById(R.id.button51);
        b3 = (Button) findViewById(R.id.button52);
        b4 = (Button) findViewById(R.id.button53);

        b1.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.skct.edu.in/docs/Cutoff/CUTOFF%20MARKS%20FOR%20THE%20YEAR%202012%20-%202013.pdf"));
                                if(in2.resolveActivity(getPackageManager()) != null)
                                {
                                    startActivity(in2);
                                }
                            }
                        }
                );
        b2.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.skct.edu.in/docs/Cutoff/CUTOFF%20MARKS%20FOR%20THE%20YEAR%202013%20-%202014.pdf"));
                                if(in2.resolveActivity(getPackageManager()) != null)
                                {
                                    startActivity(in2);
                                }
                            }
                        }
                );
        b3.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.skct.edu.in/docs/Cutoff/CUTOFF%20MARKS%20FOR%20THE%20YEAR%202014%20-%202015.pdf"));
                                if(in2.resolveActivity(getPackageManager()) != null)
                                {
                                    startActivity(in2);
                                }
                            }
                        }
                );
        b4.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.skct.edu.in/docs/Cutoff-2015-2016.pdf"));
                                if(in2.resolveActivity(getPackageManager()) != null)
                                {
                                    startActivity(in2);
                                }
                            }
                        }
                );

    }
}
