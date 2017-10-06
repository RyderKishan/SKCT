package com.example.kisha.skct;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class CugIce extends ListActivity
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
            m_parts.add(new Item("Dr. P. Manju","7402600170"));
            m_parts.add(new Item("Ms. K. Shanthi","7402600172"));
            m_parts.add(new Item("Mr. S. Dilip Kumar","7402600176"));
            m_parts.add(new Item("Ms. M. Vidya","7402600173"));
            m_parts.add(new Item("Ms. R. Swathi","7402600179"));
            m_parts.add(new Item("Ms. S. Deebika","7402600345"));
            m_parts.add(new Item("Ms. S. Jananisri","7402600346"));
            m_parts.add(new Item("Ms. M. Jayalaxmi","7402600178"));
            m_parts.add(new Item("Mr. J. Dhanaselvam","7402600174"));
            m_parts.add(new Item("Mr. Ajith B. Singh","7402600284"));
            m_parts.add(new Item("Mr. S. Bala SaravananLab Asst.","7402600177"));


            m_adapter = new ItemAdapter(CugIce.this, R.layout.property_layout, m_parts);

            // display the list.
            setListAdapter(m_adapter);
        }
    };

}
