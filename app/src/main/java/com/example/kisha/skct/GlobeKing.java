package com.example.kisha.skct;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GlobeKing extends AppCompatActivity
{
    WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_globe_king);

        wv1 = (WebView) findViewById(R.id.web_view3);
        WebSettings webSettings = wv1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv1.loadUrl("http://globeking.in/globe_post.php");
        wv1.setWebViewClient(new WebViewClient());

        wv1.setDownloadListener(new DownloadListener()
        {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength)
            {

                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        if (wv1.canGoBack())
            wv1.goBack();
        else
            super.onBackPressed();
    }
}
