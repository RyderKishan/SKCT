package com.example.kisha.skct;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class OdCheck extends AppCompatActivity
{
    ProgressBar progressBar;
    Button button;
    String Reference;
    EditText editText;
    TextView textView, textView1, textView2;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_od_check);

        editText = (EditText) findViewById(R.id.editText11);
        textView1 = (TextView) findViewById(R.id.textView157);
        textView2 = (TextView) findViewById(R.id.textView158);
        textView = (TextView) findViewById(R.id.textView159);
        progressBar = (ProgressBar) findViewById(R.id.progressBar6);
        progressBar.setVisibility(View.INVISIBLE);
        button = (Button) findViewById(R.id.button56);


    }

    public void register_register(View v)
    {
        offButtonVisibility();
        onProgressVisibility();
        Reference = editText.getText().toString();
        BackGround b = new BackGround();
        b.execute(Reference);
    }

    class BackGround extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            String cid = params[0];

            String data = "";
            int tmp;

            try
            {
                URL url = new URL("http://app1.skct.edu.in:808/skctapp/status_retrival.php");
                String urlParams = "cid=" + cid;
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
        protected void onPostExecute(String string)
        {

            String t, h;

            Log.e("Error : ", string);

                t = Character.toString(string.charAt(0));
                h = Character.toString(string.charAt(2));

                if (t.equals("0"))
                {
                    textView.setText("Your form has been submitted! Please hold on!");
                    textView1.setText("Pending!");
                    textView2.setText("Pending!");
                }

                if (t.equals("1"))
                {
                    textView1.setText("Accepted!");
                    if (h.equals("0")) {
                        textView.setText("Your form has been submitted! Please hold on!");
                        textView2.setText("Pending!");
                    }
                    if (h.equals("1")) {
                        textView.setText("Your request has been accepted! Please proceed!");
                        textView2.setText("Accepted!");
                    }
                    if (h.equals("2")) {
                        textView.setText("Sorry! Your request is denied!");
                        textView2.setText("Denied!");
                    }
                }

                if (t.equals("2"))
                {
                    textView.setText("Sorry! Your request is denied!");
                    textView1.setText("Denied!");
                    textView2.setText("Denied!");
                }

                if (string.equals("File not exists"))
                {
                    h = "Invalid Reference No!";
                    textView.setText("Give valid reference no!");
                    Toast.makeText(ctx, h, Toast.LENGTH_LONG).show();
                }
                if (string.equals("")) {
                    h = "Server Down";
                    textView.setText("");
                    Toast.makeText(ctx, h, Toast.LENGTH_LONG).show();
                }

                offProgressVisibility();
                onButtonVisibility();
                editText.setText(null);
        }
    }

    public void offButtonVisibility()
    {
        button.setVisibility(View.INVISIBLE);
    }
    public void onButtonVisibility()
    {
        button.setVisibility(View.VISIBLE);
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
