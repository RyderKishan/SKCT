package com.example.kisha.skct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kisha on 08-12-2016.
 */

public class NewsAdapter extends ArrayAdapter<News>
{
    private ArrayList<News> objects;

    public NewsAdapter(Context context, int resource, ArrayList<News> objects)
    {
        super(context, resource, objects);
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {

        View v = convertView;

        if (v == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.news_layout, null);
        }

        final News i = objects.get(position);

        if (i != null)
        {
            TextView heading = (TextView) v.findViewById(R.id.textView132);
            TextView content = (TextView) v.findViewById(R.id.textView133);
            ImageView image = (ImageView) v.findViewById(R.id.imageView50);

            if (heading != null)
            {
                heading.setText(i.getHeading());
            }
            if (content != null)
            {
                content.setText(i.getContent());
            }

            image.setImageResource(R.drawable.thumb);
        }

        return v;
    }
}
