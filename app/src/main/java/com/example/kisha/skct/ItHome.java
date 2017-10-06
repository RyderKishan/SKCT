package com.example.kisha.skct;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ItHome extends AppCompatActivity
{
    int flag = 0;
    ImageView imageView1 , imageView2, imageView;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it_home);

        final int[] resource = new int[]{R.drawable.ita, R.drawable.itb, R.drawable.itc, R.drawable.itd};

        imageView1 = (ImageView) findViewById(R.id.imageView42);
        imageView2 = (ImageView) findViewById(R.id.imageView43);
        imageView = (ImageView) findViewById(R.id.imageView41);


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
                                if(flag < 3)
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
