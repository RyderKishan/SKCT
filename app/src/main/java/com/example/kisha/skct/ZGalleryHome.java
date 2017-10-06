package com.example.kisha.skct;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class ZGalleryHome extends AppCompatActivity
{
    ListView list;
    ZLazyImageLoadAdapter adapter;
    Context context = this;
    Vibrator mVibrator;

    private String[] mStrings = {
            "http://app1.skct.edu.in:808/skctapp/gallery/ga.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gb.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gc.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gd.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/ge.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gf.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gg.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gh.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gi.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gj.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gk.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gl.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gm.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gn.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/go.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gp.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gq.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gr.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gs.jpg",
            "http://app1.skct.edu.in:808/skctapp/gallery/gt.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zgallery_home);

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        list = (ListView) findViewById(R.id.list);

        // Create custom adapter for listview
        adapter = new ZLazyImageLoadAdapter(this, mStrings);

        //Set adapter to listview
        list.setAdapter(adapter);

        Button b=(Button)findViewById(R.id.button60);
        b.setOnClickListener(listener);


    }

    @Override
    public void onDestroy()
    {
        // Remove adapter refference from list
        list.setAdapter(null);
        super.onDestroy();
    }

    public OnClickListener listener = new OnClickListener()
    {
        @Override
        public void onClick(View arg0) {

            //Refresh cache directory downloaded images
            adapter.imageLoader.clearCache();
            adapter.notifyDataSetChanged();
        }
    };

    public void onItemClick(int mPosition)
    {
        String tempValues = mStrings[mPosition];
        mVibrator.vibrate(80);
    }


}
