package com.example.kisha.skct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class DisplayProfile extends AppCompatActivity
{
    String displayName, imageUrl;
    ImageView picture;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);

        displayName = getIntent().getStringExtra("DISPLAY_NAME");
        imageUrl = getIntent().getStringExtra("PROFILE_PIC");
        name = (TextView) findViewById(R.id.textView162);
        picture = (ImageView) findViewById(R.id.imageView63);

        name.setText(displayName);
        Picasso.with(DisplayProfile.this)
                .load(imageUrl)
                .error(R.drawable.aa)
                .resize(150,150)
                .centerCrop()
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(picture);
    }
}
