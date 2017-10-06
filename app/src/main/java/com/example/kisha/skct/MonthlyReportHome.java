package com.example.kisha.skct;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.URL;

import static com.example.kisha.skct.MonthlyReportHome.change;

public class MonthlyReportHome extends AppCompatActivity
{


    public static ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_report_home);

        Button b1 = (Button) findViewById(R.id.button17);
        mProgress = (ProgressBar) findViewById(R.id.progressBar2);
        mProgress.setVisibility(View.VISIBLE);

        final ImageView iv = (ImageView) findViewById(R.id.imageView4);
        final String imgURL  = "http://www.skct.edu.in/images/keerthi_2.jpg";
        new DownLoadImageTask(iv).execute(imgURL);


        b1.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.skct.edu.in/docs/August.pdf"));
                                if(in2.resolveActivity(getPackageManager()) != null)
                                {
                                    startActivity(in2);
                                }
                            }
                        }
                );
        Button b2 = (Button) findViewById(R.id.button18);
        b2.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.skct.edu.in/docs/July.pdf"));
                                if(in2.resolveActivity(getPackageManager()) != null)
                                {
                                    startActivity(in2);
                                }
                            }
                        }
                );
        Button b3 = (Button) findViewById(R.id.button19);
        b3.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.skct.edu.in/docs/MONTHLY_REPORT_%20JANUARY_16.pdf"));
                                if(in2.resolveActivity(getPackageManager()) != null)
                                {
                                    startActivity(in2);
                                }
                            }
                        }
                );

    }
    public static void change()
    {
        mProgress.setVisibility(View.INVISIBLE);
    }
}

class DownLoadImageTask extends AsyncTask<String,Void,Bitmap>
{
    ImageView imageView;

    DownLoadImageTask(ImageView imageView)
    {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String...urls)
    {
        String urlOfImage = urls[0];
        Bitmap logo = null;
        try
        {
            InputStream is = new URL(urlOfImage).openStream();
            logo = BitmapFactory.decodeStream(is);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return logo;
    }
    protected void onPostExecute(Bitmap result)
    {
        change();
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(result);
    }
}