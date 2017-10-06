package com.example.kisha.skct;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class CugEee extends ListActivity
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
            m_parts.add(new Item("Dr. K. S. Chandra Gupta Mauryan","7402600140"));
            m_parts.add(new Item("Dr. E. Nanda Kumar","7402600141"));
            m_parts.add(new Item("Mr. G. Sivagnanam","7402600143"));
            m_parts.add(new Item("Mr. S. Vimal Raj","7402600145"));
            m_parts.add(new Item("Ms. V. S. Sanjana Devi","7402600147"));
            m_parts.add(new Item("Mr. R. Anand","7402600142"));
            m_parts.add(new Item("Mr. P. John Samuel","7402600268"));
            m_parts.add(new Item("Ms. M. Divya Priyadharshini","7402600269"));
            m_parts.add(new Item("Ms. S. Sri Ragavi","0000000000"));
            m_parts.add(new Item("Ms. J. Priyadarshini","0000000000"));
            m_parts.add(new Item("Ms. K. Nithya","7402600274"));
            m_parts.add(new Item("Ms. S. Ashadevi","7402600276"));
            m_parts.add(new Item("Ms. A. Elakya","7402600278"));
            m_parts.add(new Item("Ms. P. Kavinmalar","0000000000"));
            m_parts.add(new Item("Ms. G. Sukanya","0000000000"));
            m_parts.add(new Item("Mr. Hans John D'Cruz","7402600313"));
            m_parts.add(new Item("Ms. S. Swathy","7402600314"));
            m_parts.add(new Item("Mr. K. Vishnu Murthy","7402600315"));
            m_parts.add(new Item("Ms. P. Aruna","7402600317"));
            m_parts.add(new Item("Ms. R. Nagalakshmi","7402600318"));
            m_parts.add(new Item("Ms. K. Janani","7402600319"));
            m_parts.add(new Item("Ms. S. Abirami","7402600321"));
            m_parts.add(new Item("Ms. J. Joys Nancy","7402600322"));
            m_parts.add(new Item("Ms. P. Kausalyadevi","7402600323"));
            m_parts.add(new Item("Ms. R. Devi","7402600273"));
            m_parts.add(new Item("Mr. R. Sathish Kumar","7402600275"));
            m_parts.add(new Item("Ms. V. Kalpana","7402600277"));
            m_parts.add(new Item("Ms. R. Divya","7402600267"));
            m_parts.add(new Item("Ms. P. Swapna","7402600259"));
            m_parts.add(new Item("Ms. I. Jenifer","7402600148"));
            m_parts.add(new Item("Ms. T. Dharanika","7402600272"));
            m_parts.add(new Item("Mr. N. S. Srinath","7402600112"));
            m_parts.add(new Item("Mr. G. R. Kanagachidambaresan","7402600219"));
            m_parts.add(new Item("Mr. A. Hari Narayanan","7402600235"));
            m_parts.add(new Item("Ms. A. Little Judy","7402600167"));
            m_parts.add(new Item("Mr. S. Nagakumararaj","7402600221"));


            m_adapter = new ItemAdapter(CugEee.this, R.layout.property_layout, m_parts);

            // display the list.
            setListAdapter(m_adapter);
        }
    };

}
