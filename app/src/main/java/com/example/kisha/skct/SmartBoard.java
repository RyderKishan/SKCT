package com.example.kisha.skct;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SmartBoard extends AppCompatActivity
{

    int flag = 0;
    Context context = this;
    ImageView imageView1 , imageView2, imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_board);

        final int[] resource = new int[]{R.drawable.sma, R.drawable.smb};

        imageView1 = (ImageView) findViewById(R.id.imageView55);
        imageView2 = (ImageView) findViewById(R.id.imageView56);
        imageView = (ImageView) findViewById(R.id.imageView18);


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
                                    Toast.makeText(context, "Go Right", Toast.LENGTH_LONG).show();
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
                                if(flag < 1)
                                {
                                    imageView.setImageResource(resource[(++flag)]);
                                }
                                else
                                {
                                    Toast.makeText(context, "Go Left", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                );
    }
}
