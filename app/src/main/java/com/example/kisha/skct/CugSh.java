package com.example.kisha.skct;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class CugSh extends ListActivity
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
            m_parts.add(new Item("Dr. S. Palaniammal","7402600180"));
            m_parts.add(new Item("Ms. P. Sheeba Ranjini","7402600182"));
            m_parts.add(new Item("Ms. T. Bhavani","7402600185"));
            m_parts.add(new Item("Ms. H. Shubhajyothi","7402600184"));
            m_parts.add(new Item("Ms. L. Gomathy","7402600194"));
            m_parts.add(new Item("Ms. N. Leelavathi","7402600196"));
            m_parts.add(new Item("Mr. S. Karthik","7402600202"));
            m_parts.add(new Item("Ms. B. Haripriya","7402600203"));
            m_parts.add(new Item("Ms. R. Vakithabegam","7402600189"));
            m_parts.add(new Item("Ms. C. Kalaiselvi","7402600204"));
            m_parts.add(new Item("Ms. R. Rajalakshmi","7402600205"));
            m_parts.add(new Item("Ms. R. Abinaya","7402600206"));
            m_parts.add(new Item("Ms. V. Parimala","7402600215"));
            m_parts.add(new Item("Ms. D. Vasantha kumari","7402600217"));
            m_parts.add(new Item("Ms. B. Lavanya","7402600362"));
            m_parts.add(new Item("Ms. A. Suji Priya","7402600363"));
            m_parts.add(new Item("Mr. S. Nareshkumar","7402600365"));
            m_parts.add(new Item("Mr. D. Maheskumar","7402600218"));
            m_parts.add(new Item("Dr. K.R. Kanimozhi","7402600183"));
            m_parts.add(new Item("Mr. D. Santhosh Shanthakumar","7402600186"));
            m_parts.add(new Item("Ms. S.S. Sabithamala","7402600213"));
            m_parts.add(new Item("Ms. L. Suganya","7402600197"));
            m_parts.add(new Item("Mr. S. Prabux","7402600214"));
            m_parts.add(new Item("Ms. N. Nalini","7402600214"));
            m_parts.add(new Item("Ms. R. Uthayamalar","7402600367"));
            m_parts.add(new Item("Ms. K. Shanthi","7402600187"));
            m_parts.add(new Item("Ms. D. Anitha Jennifer","7402600195"));
            m_parts.add(new Item("Mr. V. Mari Anandha Kumar","7402600199"));
            m_parts.add(new Item("Ms. R. Shanthamani","7402600181"));
            m_parts.add(new Item("Ms. K. Kavitharaj","7402600216"));
            m_parts.add(new Item("Mr. D. Pradeek","7402600283"));
            m_parts.add(new Item("Dr. Nidhi Sharma","7402600246"));
            m_parts.add(new Item("Ms. S. Rahini","7402600144"));


            m_adapter = new ItemAdapter(CugSh.this, R.layout.property_layout, m_parts);

            // display the list.
            setListAdapter(m_adapter);
        }
    };

}
