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
 * Created by kisha on 07-12-2016.
 */

public class RecruiterAdapter extends ArrayAdapter<Recuit>
{

    private ArrayList<Recuit> objects;

    public RecruiterAdapter(Context context, int textViewResourceId, ArrayList<Recuit> objects)
    {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if (v == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.recruiter_layout, null);
        }

        final Recuit i = objects.get(position);

        if (i != null)
        {

            TextView name = (TextView) v.findViewById(R.id.textView68);
            ImageView image = (ImageView) v.findViewById(R.id.imageView19);

            if (name != null)
            {
                name.setText(i.getName());
            }

            image.setImageResource(i.getNo());
        }
        return v;
    }
}
