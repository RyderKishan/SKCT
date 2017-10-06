package com.example.kisha.skct;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class CugIt extends ListActivity
{
    private ArrayList<Item> m_parts = new ArrayList<Item>();
    private Runnable viewParts;
    private ItemAdapter m_adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        // instantiate our ItemAdapter class
        m_adapter = new ItemAdapter(this, R.layout.property_layout, m_parts);
        setListAdapter(m_adapter);

        // here we are defining our runnable thread.
        viewParts = new Runnable(){
            public void run(){
                handler.sendEmptyMessage(0);
            }
        };

        // here we call the thread we just defined - it is sent to the handler below.
        Thread thread =  new Thread(null, viewParts, "MagentoBackground");
        thread.start();
    }

    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            // create some objects
            // here is where you could also request data from a server
            // and then create objects from that data.
            m_parts.add(new Item("Dr. A. Jameer Basha","7402600160"));
            m_parts.add(new Item("Dr. R. Kanmani","7402600161"));
            m_parts.add(new Item("Ms. R. Suganya","000000000"));
            m_parts.add(new Item("Ms. A. Christy Jeba Malar","7402600163"));
            m_parts.add(new Item("Ms. M. Sangeetha","7402600164"));
            m_parts.add(new Item("Mr. A. Suresh Kumar","7402600165"));
            m_parts.add(new Item("Ms. G. Lavanya","7402600166"));
            m_parts.add(new Item("Ms. T. Sangeetha","7402600168"));
            m_parts.add(new Item("Ms. S. Muthu Lakshmi","0000000000"));
            m_parts.add(new Item("Ms. S. Subhakala","7402600222"));
            m_parts.add(new Item("Ms. P. Poongodi","7402600223"));
            m_parts.add(new Item("Ms. K. Mythili","7402600224"));
            m_parts.add(new Item("Ms. G. Aishwaryalakshmi","7402600225"));
            m_parts.add(new Item("Ms. G. V. Aarthi","7402600226"));
            m_parts.add(new Item("Ms. D. Ranjani","7402600227"));
            m_parts.add(new Item("Ms. M. Malathi","7402600228"));
            m_parts.add(new Item("Ms. M. Meenalochini","7402600229"));
            m_parts.add(new Item("Ms. K. Induja","7402600360"));
            m_parts.add(new Item("Ms. V. Roopa","7402600361"));
            m_parts.add(new Item("Ms. A. Geetha","7202600293"));
            m_parts.add(new Item("Ms. S. Barkath Nisha","7402600169"));
            m_parts.add(new Item("Mr. M. Kowsigan","7402600366"));
            m_parts.add(new Item("Mr. T. Rajesh Kumar","7402600201"));
            m_parts.add(new Item("Ms. M. Lavanya","7402600364"));
            m_parts.add(new Item("Ms. K. Dhanya","7402600162"));
            m_parts.add(new Item("Ms. S. Suguna","7402600209"));
            m_parts.add(new Item("Ms. S. Surya","7402600335"));


            m_adapter = new ItemAdapter(CugIt.this, R.layout.property_layout, m_parts);

            // display the list.
            setListAdapter(m_adapter);
        }
    };

}
