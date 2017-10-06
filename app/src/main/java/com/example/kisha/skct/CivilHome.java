package com.example.kisha.skct;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class CivilHome extends AppCompatActivity
{
    int flag = 0;
    ImageView imageView1 , imageView2, imageView;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civil_home);

        final int[] resource = new int[]{R.drawable.ca, R.drawable.cb, R.drawable.cc};

        imageView1 = (ImageView) findViewById(R.id.imageView30);
        imageView2 = (ImageView) findViewById(R.id.imageView31);
        imageView = (ImageView) findViewById(R.id.imageView29);


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
    }
}
