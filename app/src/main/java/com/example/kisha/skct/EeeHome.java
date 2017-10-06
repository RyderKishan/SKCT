package com.example.kisha.skct;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class EeeHome extends AppCompatActivity
{
    int flag = 0;
    ImageView imageView1 , imageView2, imageView;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eee_home);

        Button b1 = (Button) findViewById(R.id.button41);
        Button b2 = (Button) findViewById(R.id.button42);

        final int[] resource = new int[]{R.drawable.eea, R.drawable.eeb, R.drawable.eec};

        imageView1 = (ImageView) findViewById(R.id.imageView36);
        imageView2 = (ImageView) findViewById(R.id.imageView37);
        imageView = (ImageView) findViewById(R.id.imageView35);


        imageView1.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                if(flag > 0)
                                {
                                    imageView.setImageResource(resource[(--flag)]);
                                }
                                else
                                {
                                    Toast.makeText(ctx, "Go Right", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                );

        imageView2.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                if(flag < 2)
                                {
                                    imageView.setImageResource(resource[(++flag)]);
                                }
                                else
                                {
                                    Toast.makeText(ctx, "Go Left", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                );

        b1.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.skct.edu.in/curriculam/EEE.pdf"));
                                if(in.resolveActivity(getPackageManager()) != null)
                                {
                                    startActivity(in);
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
                                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.skct.edu.in/curriculam/15-ME%20PSE%20CURRICULUM.pdf"));
                                if(in.resolveActivity(getPackageManager()) != null)
                                {
                                    startActivity(in);
                                }
                            }
                        }
                );



    }
}
