package com.example.kisha.skct;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.kisha.skct.MainActivity.auth;

public class FeedbackHome extends AppCompatActivity
{
    EditText editText;
    ProgressBar progressBar;
    Button button;
    String feedback;
    Context ctx = this;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_home);

        user = auth.getCurrentUser();

        editText = (EditText) findViewById(R.id.editText13);
        progressBar = (ProgressBar) findViewById(R.id.progressBar9);
        button = (Button) findViewById(R.id.button58);

        button.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                register_register();
                            }
                        }
                );

    }

    public void register_register()
    {
        feedback = editText.getText().toString();
        BackGround b = new BackGround();
        b.execute(feedback+"\n\n"+"This feedback is given by : "+user.getDisplayName()+" - "+user.getEmail());
        editText.setText(null);
        button.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    class BackGround extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            String feed = params[0];
            String data="";
            int tmp;

            try
            {
                URL url = new URL("http://app1.skct.edu.in:808/skctapp/skct_feed.php");
                String urlParams = "feed="+feed;

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
            if (s.startsWith("Excep"))
            {
                progressBar.setVisibility(View.INVISIBLE);
                button.setVisibility(View.VISIBLE);
                Toast.makeText(ctx, "Failed to submit!", Toast.LENGTH_SHORT).show();
                editText.setText(feedback);
            }
            else {
                if (s.equals("ok")) {
                    progressBar.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.VISIBLE);
                    Toast.makeText(ctx, "Success !", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.VISIBLE);
                    Toast.makeText(ctx, "Failed to submit!", Toast.LENGTH_SHORT).show();
                    editText.setText(feedback);
                }
            }
        }
    }

}
