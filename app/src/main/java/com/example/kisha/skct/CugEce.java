package com.example.kisha.skct;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class CugEce extends ListActivity
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

            m_parts.add(new Item("Dr. R. Udaiya Kumar","7402600130"));
            m_parts.add(new Item("Dr. R. Maheswar","7402600132"));
            m_parts.add(new Item("Dr. R. Vadivelu","7402600133"));
            m_parts.add(new Item("Dr. Senoj Joseph","7402600134"));
            m_parts.add(new Item("Ms. G. Anitha","7402600138"));
            m_parts.add(new Item("Mr. P. Jayarajan","7402600135"));
            m_parts.add(new Item("Mr. U. Venkateshkumar","7402600137"));
            m_parts.add(new Item("Mr. M. R.Thiyagupriyadharsan","7402600136"));
            m_parts.add(new Item("Ms. Yamini Shanmugam","0000000000"));
            m_parts.add(new Item("Ms. M. Jaishree","7402600255"));
            m_parts.add(new Item("Mr. N. Arun Prasath","7402600251"));
            m_parts.add(new Item("Ms. S. Thenmozhi","0000000000"));
            m_parts.add(new Item("Mr. Z. Ahamed Yasar","7402600256"));
            m_parts.add(new Item("Ms. S. Vaishnavi","0000000000"));
            m_parts.add(new Item("Ms. J. Abiramiathavi","7402600264"));
            m_parts.add(new Item("Ms. R. Priya","7402600261"));
            m_parts.add(new Item("Ms. P. Saranya","7402600262"));
            m_parts.add(new Item("Mr. G. Santhakumar","7402600263"));
            m_parts.add(new Item("Ms. P. Nithya","0000000000"));
            m_parts.add(new Item("Ms. D. Nageswari","0000000000"));
            m_parts.add(new Item("Ms. K. Divya","7402600266"));
            m_parts.add(new Item("Ms. S. Jaipriya","7402600248"));
            m_parts.add(new Item("Ms. G. Dhivyasri","7402600335"));
            m_parts.add(new Item("Mr. S. Ganesh Prabhu","7402600336"));
            m_parts.add(new Item("Ms. S. Kiruthiga","7402600338"));
            m_parts.add(new Item("Ms. K. Pradeepa","7402600320"));
            m_parts.add(new Item("Mr. K. Srinivasan","7402600330"));
            m_parts.add(new Item("Ms. K. Subashree","7402600254"));
            m_parts.add(new Item("Ms. S. Sarika","7402600339"));
            m_parts.add(new Item("Ms. A. Kirthi","7402600139"));
            m_parts.add(new Item("Ms. A. M. Aswini Priyadharshini","7402600252"));
            m_parts.add(new Item("Ms. V. Poonkulalai","7402600245"));
            m_parts.add(new Item("Ms. T. Happila","7402600334"));
            m_parts.add(new Item("Ms. B. Priyanka","7402600337"));
            m_parts.add(new Item("Ms. L. Charline Karunya","7402600265"));
            m_parts.add(new Item("Ms. S. Shalini","7402600131"));
            m_parts.add(new Item("Ms. T. Janani","7402600247"));
            m_parts.add(new Item("Mr. M. Navin Kumar","7402600249"));
            m_parts.add(new Item("Ms. N. Agnes Shiny Rachel","7402600257"));


            m_adapter = new ItemAdapter(CugEce.this, R.layout.property_layout, m_parts);

            // display the list.
            setListAdapter(m_adapter);
        }
    };

}
