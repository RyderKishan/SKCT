package com.example.kisha.skct;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class CugCivil extends ListActivity
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
            m_parts.add(new Item("Dr. I. Padmanaban","7402600120"));
            m_parts.add(new Item("Dr. M. Lenin Sundar","7402600171"));
            m_parts.add(new Item("Dr. V. Sreevidya","7402600122"));
            m_parts.add(new Item("Dr. S. Hema","7402600344"));
            m_parts.add(new Item("Ms. A. Vennila","7402600123"));
            m_parts.add(new Item("Dr. M. Devasena","7402600124"));
            m_parts.add(new Item("Ms. A. Sangeetha","7402600126"));
            m_parts.add(new Item("Mr. S. Venkatesh","7402600128"));
            m_parts.add(new Item("Mr. A. Sivajothi","7402600127"));
            m_parts.add(new Item("Ms. Geethu Mohan","7402600280"));
            m_parts.add(new Item("Mr. R. Ramesh","7402600231"));
            m_parts.add(new Item("Ms. B. I. Sonia","7402600129"));
            m_parts.add(new Item("Ms. S. Sasikala","7402600342"));
            m_parts.add(new Item("Mr. T.P.A. Aravind","7402600193"));
            m_parts.add(new Item("Mr. K.R. Keerthi Raman","7402600341"));
            m_parts.add(new Item("Ms. S.Muthu Keerthana","7402600347"));


            m_adapter = new ItemAdapter(CugCivil.this, R.layout.property_layout, m_parts);

            // display the list.
            setListAdapter(m_adapter);
        }
    };

}
