package com.example.kisha.skct;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.kisha.skct.MainActivity.auth;


public class CalendarDisplay extends AppCompatActivity
{
    CalendarView calendarView;
    TextView textView;
    EditText editText;
    Button button;
    ProgressBar progressBar;
    String string, display;
    Context ctx = this;
    int d,m,y;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_display);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setShowWeekNumber(false);
        textView = (TextView) findViewById(R.id.textView141);
        progressBar = (ProgressBar) findViewById(R.id.progressBar7);
        button = (Button) findViewById(R.id.button57);
        editText = (EditText) findViewById(R.id.editText12);

        button.setVisibility(View.INVISIBLE);
        editText.setVisibility(View.INVISIBLE);

        button.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {

                                display = editText.getText().toString();
                                editText.setVisibility(View.INVISIBLE);
                                button.setVisibility(View.INVISIBLE);
                                onProgressVisibility();
                                register(getCurrentFocus());
                            }
                        }
                );

        textView.setText("No events!");

        offProgressVisibility();
        onButtonVisibility();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day)
            {

                editText.setVisibility(View.INVISIBLE);
                button.setVisibility(View.INVISIBLE);
                textView.setText(null);
                onProgressVisibility();
                d = day;
                m = month+1;
                //Log.e("Year",m);
                y = year;
                string = "s"+d+m+y;
                register_register(getCurrentFocus());
            }
        });

    }

    public void register_register(View v)
    {
        BackGround b = new BackGround();
        b.execute(string);
    }

    public void register(View v)
    {
        BackGroundTask backGroundTask = new BackGroundTask();
        editText.setText(null);
        backGroundTask.execute(string, display);
    }

    class BackGround extends AsyncTask<String, String, String>
{
    @Override
    protected String doInBackground(String... params)
    {
        String sdate = params[0];

        String data = "";
        int tmp;

        try
        {
            URL url = new URL("http://app1.skct.edu.in:808/skctapp/calender.php");
            String urlParams = "sdate=" + sdate;
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            os.write(urlParams.getBytes());
            os.flush();
            os.close();
            InputStream is = httpURLConnection.getInputStream();
            while ((tmp = is.read()) != -1)
            {
                data += (char) tmp;
            }
            is.close();
            httpURLConnection.disconnect();
            return data;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return "You are offline!";
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "You are offline!";
        }
    }


    @Override
    protected void onPostExecute(String s)
    {
        if (!(s.equals("no")))
        {
            offProgressVisibility();
            onButtonVisibility();
            textView.setText(s);
        }
        else
        {
            if (s.equals("no"))
            {
                if(auth.getCurrentUser().getDisplayName().equals("Balkishan"))
                {
                    textView.setText(null);
                    textView.setVisibility(View.INVISIBLE);
                    editText.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                }
                else
                {
                    editText.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("No events!");
                }

            }
            if (s.equals(""))
            {
                s = "No response from server";
                Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
            }
            offProgressVisibility();
            onButtonVisibility();
        }
    }
}
    class BackGroundTask extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            String sdate = params[0];
            String sdata = params[1];

            String data = "";
            int tmp;

            try
            {
                URL url = new URL("http://app1.skct.edu.in:808/skctapp/calender_add.php");
                String urlParams = "sdate=" + sdate + "&sdata=" + sdata;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1)
                {
                    data += (char) tmp;
                }
                is.close();
                httpURLConnection.disconnect();
                return data;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }


        @Override
        protected void onPostExecute(String s)
        {
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            if (s.equals("success"))
            {
                editText.setVisibility(View.INVISIBLE);
                button.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                textView.setText(display);
                textView.setVisibility(View.VISIBLE);
            }
            else
            {
                s = "No response from server";
                Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void offButtonVisibility()
    {
        textView.setVisibility(View.INVISIBLE);
    }
    public void onButtonVisibility()
    {
        textView.setVisibility(View.VISIBLE);
    }
    public void offProgressVisibility()
    {
        progressBar.setVisibility(View.INVISIBLE);
    }
    public void onProgressVisibility()
    {
        progressBar.setVisibility(View.VISIBLE);
    }
}
