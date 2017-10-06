package com.example.kisha.skct;


import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class CugCse extends ListActivity
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
            m_parts.add(new Item("Dr. A. Balamurugan","7402600150"));
            m_parts.add(new Item("Dr. P. Tamije Selvy","7402600151"));
            m_parts.add(new Item("Dr. R. Subha","7402600152"));
            m_parts.add(new Item("Ms. S. Bhuvana","7402600153"));
            m_parts.add(new Item("Mr. S. Anto","7402600154"));
            m_parts.add(new Item("Ms. P. Anantha Prabha","7402600155"));
            m_parts.add(new Item("Ms. PL. Rajarajeswari","7402600156"));
            m_parts.add(new Item("Mr. P. Madhavan","7402600157"));
            m_parts.add(new Item("Ms. E. Merlin Mercy","7402600159"));
            m_parts.add(new Item("Ms. S. Siamala Devi","7402600158"));
            m_parts.add(new Item("Dr. M. Deva Priya","7402600282"));
            m_parts.add(new Item("Ms. S. Vaishnavee","7402600286"));
            m_parts.add(new Item("Ms. P. Kalpana","7402600285"));
            m_parts.add(new Item("Ms. E. Dhivyaprabha","7402600288"));
            m_parts.add(new Item("Ms. P. Ranjitha","7402600289"));
            m_parts.add(new Item("Ms. K. R. Sarumathi","7402600291"));
            m_parts.add(new Item("Ms. M. Dhivyashree","7402600296"));
            m_parts.add(new Item("Ms. J. Santhiya","7402600298"));
            m_parts.add(new Item("Ms. D. Sharmila Rani","7402600299"));
            m_parts.add(new Item("Ms. R. Asmitha Shree","7402600302"));
            m_parts.add(new Item("Ms. N. Kiruthiga","7402600303"));
            m_parts.add(new Item("Ms. R. Sumathy","7402600304"));
            m_parts.add(new Item("Ms. U. L. Sindhu","7402600305"));
            m_parts.add(new Item("Ms. R. Sujatha","7402600306"));
            m_parts.add(new Item("Ms. R. Anitha Nithya","7402600307"));
            m_parts.add(new Item("Ms. F. Femila","7402600308"));
            m_parts.add(new Item("Ms. R. Anitha","7402600312"));
            m_parts.add(new Item("Ms. J. Sharmila","7402600287"));
            m_parts.add(new Item("Ms. S. Ramila","7402600340"));
            m_parts.add(new Item("Ms. S. Nivedha","7402600359"));
            m_parts.add(new Item("Ms. M. Jeevitha","7402600211"));
            m_parts.add(new Item("Ms. T. Suganya","7402600301"));
            m_parts.add(new Item("Ms. R. B. Janani","7402600208"));
            m_parts.add(new Item("Ms. S. Arul Selvi","7402600311"));
            m_parts.add(new Item("Ms. F. Ragini Felicia Surthi","7402600297"));
            m_parts.add(new Item("Ms. S. Anu","7402600309"));
            m_parts.add(new Item("Ms. A. Sunitha Nandhini","7402600294"));
            m_parts.add(new Item("Mr. S. Prakash Kumar","7402600207"));
            m_parts.add(new Item("Ms. S. Gomathi","7402600340"));
            m_parts.add(new Item("Mr. A. Sampath Kumar","7402600243"));


            m_adapter = new ItemAdapter(CugCse.this, R.layout.property_layout, m_parts);

            // display the list.
            setListAdapter(m_adapter);
        }
    };

}
