package com.example.kisha.skct;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kisha on 15-10-2016.
 */

public class ItemAdapter extends ArrayAdapter<Item>
{
    private ArrayList<Item> objects;

    public ItemAdapter(Context context, int textViewResourceId, ArrayList<Item> objects)
    {
        super(context, textViewResourceId, objects);
        this.objects = objects;

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.property_layout, null);
        }

        final Item i = objects.get(position);
        String ph = null;

        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            ImageView image = (ImageView) v.findViewById(R.id.imageView12);
            TextView name = (TextView) v.findViewById(R.id.textView92);
            TextView no = (TextView) v.findViewById(R.id.textView93);
            RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.cug_relative);

            // check to see if each individual textview is null.
            // if not, assign some text!
            if (name != null)
            {
                name.setText(i.getName());
            }
            if (no != null)
            {
                ph = i.getNo();
                String temp = "CUG No : "+ph;
                no.setText(temp);
            }
            image.setImageResource(R.drawable.aa);

            final String phno = ph;

            rl.setOnClickListener
                    (
                            new View.OnClickListener()
                            {
                                public void onClick(View v)
                                {
                                    Uri number = Uri.parse("tel:"+phno);
                                    Intent callIntent = new Intent(Intent.ACTION_CALL, number);
                                    getContext().startActivity(callIntent);
                                }
                            }
                    );


        }

        // the view must be returned to our activity
        return v;


    }
}