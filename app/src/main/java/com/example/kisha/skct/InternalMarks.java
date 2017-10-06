package com.example.kisha.skct;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.kisha.skct.MainActivity.userEmail;
import static com.example.kisha.skct.MainActivity.userName;
import static com.example.kisha.skct.MainActivity.userRollNo;

public class InternalMarks extends AppCompatActivity
{
    String roll,year,dept,filename;
    EditText editText;
    TextView textView,textView2, textView3;
    ProgressBar progressBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_marks);

        editText = (EditText) findViewById(R.id.editText14);
        button = (Button) findViewById(R.id.button62);
        textView = (TextView) findViewById(R.id.textView160);
        textView2 = (TextView) findViewById(R.id.textView161);
        textView3 = (TextView) findViewById(R.id.textView165);
        progressBar = (ProgressBar) findViewById(R.id.progressBar11);

        editText.setText(userRollNo);

        textView.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);


        Spinner spinner = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.department,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        switch (position)
                        {
                            case 0:
                                dept = "m";
                                break;
                            case 1:
                                dept = "c";
                                break;
                            case 2:
                                dept = "e";
                                break;
                            case 3:
                                dept = "l";
                                break;
                            case 4:
                                dept = "p";
                                break;
                            case 5:
                                dept = "n";
                                break;
                            case 6:
                                dept = "s";
                                break;
                        }
                    }

                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner5);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.years,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        switch (position)
                        {
                            case 0:
                                year = "16";
                                break;
                            case 1:
                                year = "15";
                                break;
                            case 2:
                                year = "14";
                                break;
                            case 3:
                                year = "13";
                                break;
                        }
                    }

                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });


    }
    public void getMarks(View v)
    {
        progressBar.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);
        roll = editText.getText().toString();
        filename = year+dept;
        BackGround b = new BackGround();
        Log.e("File name : ",filename);
        Log.e("Roll : ",roll.toLowerCase().trim());
        if (roll.startsWith("admin") || roll.startsWith("Admin"))
        {
            b.execute(filename,roll.substring(5));
            Toast.makeText(getApplicationContext(), roll.substring(5), Toast.LENGTH_SHORT).show();
        }
        else
        {
            b.execute(filename,userRollNo.trim());
        }


    }
    class BackGround extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            String data = "";
            int tmp;

            try
            {
                URL url = new URL("http://app1.skct.edu.in:808/skctapp/courseRetrival.php");
                String urlParams = "filename=" + params[0] + "&roll=" + params[1];
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
            Log.e("Error : ",s);
            if (s.startsWith("Exception"))
            {
                Toast.makeText(getApplicationContext(), "Can`t connect to server!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if (s.equals("Marks not updated"))
                {
                    Toast.makeText(getApplicationContext(), "Marks not updated", Toast.LENGTH_SHORT).show();
                    button.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                if (s.equals("roll_invalid"))
                {
                    Toast.makeText(getApplicationContext(), "Invalid roll no!", Toast.LENGTH_SHORT).show();
                    button.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                else
                {
                    int index;
                    index = s.indexOf('#');
                    String subject = s.substring(0,index);
                    String marks = s.substring(index+1);
                    Log.e("SUBJECT MARKS : ",marks);
                    String splitsub = subject.replace(',','\n');
                    String splitmark = marks.replace('\t','\n');
                    textView.setText(splitsub);
                    textView2.setText(splitmark);
                    textView3.setText(userName+" - "+userEmail);

                }
            }
            button.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView3.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            editText.setText(userRollNo);


        }
    }
}
