package com.example.kisha.skct;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ContactUsHome extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_home);

        TextView tv = (TextView) findViewById(R.id.textView87);
        TextView tv1 = (TextView) findViewById(R.id.textView88);
        TextView tv2 = (TextView) findViewById(R.id.textView89);
        TextView tv3 = (TextView) findViewById(R.id.textView86);

        tv3.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Uri number = Uri.parse("tel:04222604567");
                                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                                startActivity(callIntent);
                            }
                        }
                );

        tv.setOnClickListener
            (
                    new View.OnClickListener()
                    {
                        public void onClick(View v)
                        {
                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                    "mailto","info@skct.edu.in", null));
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sending from SKCT app");
                            startActivity(Intent.createChooser(emailIntent, "Send Email"));
                        }
                    }
            );

        tv1.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.skct.edu.in"));
                                startActivity(browserIntent);
                            }
                        }
                );
        tv2.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.srikrishna.ac.in"));
                                startActivity(Intent.createChooser(browserIntent, "Open SriKrishna"));
                            }
                        }
                );




    }
}