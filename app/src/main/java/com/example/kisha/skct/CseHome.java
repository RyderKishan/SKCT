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

public class CseHome extends AppCompatActivity
{
    int flag = 0;
    ImageView imageView1 , imageView2, imageView;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cse_home);

        Button b1 = (Button) findViewById(R.id.button40);

        final int[] resource = new int[]{R.drawable.csaa, R.drawable.csb, R.drawable.csc, R.drawable.csd, R.drawable.cse};

        imageView1 = (ImageView) findViewById(R.id.imageView24);
        imageView2 = (ImageView) findViewById(R.id.imageView25);
        imageView = (ImageView) findViewById(R.id.imageView23);


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
                                if(flag < 4)
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
                                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.skct.edu.in/curriculam/CSE.pdf"));
                                if(in.resolveActivity(getPackageManager()) != null)
                                {
                                    startActivity(in);
                                }
                            }
                        }
                );
    }
}
