package com.example.kisha.skct;


import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class CugOffice extends ListActivity
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
            m_parts.add(new Item("Mr.G.Thulasi Akilan","7402600101"));
            m_parts.add(new Item("Mr.A.Mathan Kumar","7402600102"));
            m_parts.add(new Item("Ms.K.Jeyanthi","7402600121"));
            m_parts.add(new Item("Mr.A.Abraham","7402600270"));
            m_parts.add(new Item("Mr.R.Murugesan","7402600109"));
            m_parts.add(new Item("Reception","7402600200"));
            m_parts.add(new Item("Mr.M.Bakthavachalam","7402600103"));

            m_adapter = new ItemAdapter(CugOffice.this, R.layout.property_layout, m_parts);

            // display the list.
            setListAdapter(m_adapter);
        }
    };

}
