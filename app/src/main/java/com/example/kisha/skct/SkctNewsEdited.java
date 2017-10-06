package com.example.kisha.skct;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SkctNewsEdited extends AppCompatActivity
{

    TextView heading, content, textView3;
    ProgressBar progressBar;
    ImageView imageView;
    FloatingActionButton floatingActionButton;
    Context ctx = this;
    String head, cont;
    int fname = 1000, size;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skct_news_edited);

        heading = (TextView) findViewById(R.id.textView142);
        content = (TextView) findViewById(R.id.textView143);
        textView3 = (TextView) findViewById(R.id.textView144);
        imageView = (ImageView) findViewById(R.id.imageView52);
        progressBar = (ProgressBar) findViewById(R.id.progressBar8);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        heading.setVisibility(View.INVISIBLE);
        content.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.INVISIBLE);

        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null)
        {
            heading.setVisibility(View.INVISIBLE);
            content.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
            floatingActionButton.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            get_news_count("count");
        }
        else
        {
            // not connected to the internet
            heading.setVisibility(View.INVISIBLE);
            content.setVisibility(View.INVISIBLE);
            textView3.setVisibility(View.INVISIBLE);
            floatingActionButton.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            Toast.makeText(ctx, "No internet connection", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.VISIBLE);
        }





        floatingActionButton.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                if (fname == 1000)
                                {
                                    fname = 1000 + size;
                                }

                                textView3.setVisibility(View.INVISIBLE);
                                imageView.setVisibility(View.INVISIBLE);
                                progressBar.setVisibility(View.VISIBLE);
                                heading.setVisibility(View.INVISIBLE);
                                content.setVisibility(View.INVISIBLE);
                                register_register(getCurrentFocus(), Integer.toString(fname));
                                fname--;
                            }
                        }
                );

    }

    public void register_register(View v, String send)
    {

        BackGround b = new BackGround();
        b.execute(send);
    }

    class BackGround extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            String news = params[0];
            String data="";
            int tmp;

            try
            {
                URL url = new URL("http://app1.skct.edu.in:808/skctapp/skct_news.php");
                String urlParams = "news="+news;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1)
                {
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();
                return data;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        public void onPostExecute(String s)
        {
            Log.e("News reply",s);
            if(s.startsWith("Exception: ") || s.equals("") || s == null)
            {
                heading.setText("You are offline!");
                content.setText("Could not resolve the server");
                progressBar.setVisibility(View.INVISIBLE);
                heading.setVisibility(View.VISIBLE);
                content.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
            }
            else
            {
                String[] input = s.split("%_%");
                head = input[0];
                cont = input[1];
                heading.setText(head);
                content.setText(cont);
                progressBar.setVisibility(View.INVISIBLE);
                heading.setVisibility(View.VISIBLE);
                content.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), Integer.toString(fname-999)+" / "+Integer.toString(size),Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void get_news_count(String send)
    {

        BackGroundNews b = new BackGroundNews();
        b.execute(send);
    }
    class BackGroundNews extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            String news = params[0];
            String data="";
            int tmp;

            try
            {
                URL url = new URL("http://app1.skct.edu.in:808/skctapp/news_count.php");
                String urlParams = "counter="+news;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1)
                {
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();
                return data;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        public void onPostExecute(String s)
        {
            if(s.startsWith("Exception") || s.equals("") || s == null)
            {
                Toast.makeText(ctx, "Can`t get news!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                textView3.setVisibility(View.VISIBLE);
            }
            else
            {
                if(s.equals("no"))
                {
                    Toast.makeText(ctx, "No news found!", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                }
                else
                {
                    try
                    {
                        size = Integer.parseInt(s);
                    }
                    catch (Exception e)
                    {
                        Log.e("SKCT NEWS EDITED : ", e.getMessage());
                    }
                    fname = 1000 + size;
                    progressBar.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    floatingActionButton.setVisibility(View.VISIBLE);

                }
            }




        }
    }
}
