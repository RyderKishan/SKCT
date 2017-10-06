package com.example.kisha.skct;

import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateHome extends AppCompatActivity
{
    String verName, link, getApp, getLog;
    int verCode, getVerCode;
    TextView textView, textView2 ,textView3, textView4;
    Button button;
    ProgressBar progressBar;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_home);



        textView = (TextView) findViewById(R.id.textView153);
        textView2 = (TextView) findViewById(R.id.textView154);
        textView3 = (TextView) findViewById(R.id.textView155);
        textView4 = (TextView) findViewById(R.id.textView156);
        button = (Button) findViewById(R.id.button59);
        progressBar = (ProgressBar) findViewById(R.id.progressBar10);

        link = "http://app1.skct.edu.in:808/skctapp/update";

        try
        {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            verName = packageInfo.versionName;
            verCode = packageInfo.versionCode;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }


        textView.setText(verName);


        button.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {


                                String url = link + "/" + getApp;
                                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                                request.setDescription("App Update");
                                request.setTitle("SKCT APP");

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                                {
                                    request.allowScanningByMediaScanner();
                                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                }
                                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, getApp);
                                DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                manager.enqueue(request);

                                Toast.makeText(ctx, "Downloading update! - check notification panel", Toast.LENGTH_SHORT).show();
                                button.setVisibility(View.INVISIBLE);
                                progressBar.setVisibility(View.VISIBLE);
                            }
                        }
                );

        textView2.setVisibility(View.INVISIBLE);
        textView3.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);

        register_register();


    }

    public void register_register()
    {
        BackGround b = new BackGround();
        b.execute(Integer.toString(verCode));
    }

    class BackGround extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            String data="";
            int tmp;

            try
            {
                URL url = new URL("http://app1.skct.edu.in:808/skctapp/update.php");
                String urlParams = "ver="+params[0];

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
            if(s.startsWith("Exception: ") || s.equals("") || s==null)
            {
                Toast.makeText(getApplicationContext(), "Error checking updates!", Toast.LENGTH_SHORT).show();
                textView2.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
            else
            {
                String[] input = s.split("%");
                try
                {
                    getVerCode = Integer.parseInt(input[0]);
                    getApp = input[1];
                    getLog = input[2];
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                if (verCode < getVerCode)
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    textView2.setText("Update available : "+getApp);
                    textView4.setText(getLog);
                }
                else if (verCode == getVerCode)
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    textView4.setVisibility(View.VISIBLE);
                    textView4.setText(getLog);
                }
            }

        }
    }
}