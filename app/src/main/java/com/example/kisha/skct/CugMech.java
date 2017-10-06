package com.example.kisha.skct;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class CugMech extends ListActivity
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
            m_parts.add(new Item("Dr. N. Natarajan","7402600110"));
            m_parts.add(new Item("Dr. S. Sundararaj","7402600310"));
            m_parts.add(new Item("Mr. R. Srinivasan","7402600111"));
            m_parts.add(new Item("Mr. T. Pridhar","7402600113"));
            m_parts.add(new Item("Mr. B. Suresh Babu","7402600115"));
            m_parts.add(new Item("Mr. S. Santhosh Kumar","7402600117"));
            m_parts.add(new Item("Mr. A. Athithanambi","7402600118"));
            m_parts.add(new Item("Mr. S. Arivazhagan","7402600119"));
            m_parts.add(new Item("Mr. P. Krishna Kumar","7402600232"));
            m_parts.add(new Item("Mr. N. Aravindkumar","7402600324"));
            m_parts.add(new Item("Mr. R. Arunkumar","7402600326"));
            m_parts.add(new Item("Mr. K. Vickram","7402600327"));
            m_parts.add(new Item("Mr. N. Mohan Raj","7402600233"));
            m_parts.add(new Item("Mr. K. Nirmal Kumar","7402600238"));
            m_parts.add(new Item("Mr. M. Raguramsingh","7402600237"));
            m_parts.add(new Item("Mr. R. Rathish","7402600241"));
            m_parts.add(new Item("Mr. S. Vinodh Kumar","7402600242"));
            m_parts.add(new Item("Mr. S. Dhayaneethi","7402600328"));
            m_parts.add(new Item("Mr. C. Boopathi","7402600329"));
            m_parts.add(new Item("Mr. P. Arun Karthick","7402600331"));
            m_parts.add(new Item("Mr. Suresh Isravel","7402600114"));
            m_parts.add(new Item("Mr. S. Thiyagu","7402600149"));
            m_parts.add(new Item("Mr. V. Vadivel Vivek","7402600239"));
            m_parts.add(new Item("Mr. G. Sathish Sharma","7402600332"));
            m_parts.add(new Item("Mr. K. Mohan","7402600234"));
            m_parts.add(new Item("Mr. K. Kaviyarasan","7402600116"));
            m_parts.add(new Item("Mr. R. Manivannan","7402600343"));
            m_parts.add(new Item("Mr. R. Dhivagar","7402600333"));
            m_parts.add(new Item("Mr. S. Ram Kumar","7402600271"));
            m_parts.add(new Item("Mr. S. Pradeep","7402600292"));
            m_parts.add(new Item("Mr. R. Nelson","7402600190"));
            m_parts.add(new Item("Mr. K. S. Raghul","7402600281"));


            m_adapter = new ItemAdapter(CugMech.this, R.layout.property_layout, m_parts);

            // display the list.
            setListAdapter(m_adapter);
        }
    };

}
