package com.example.kisha.skct;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class Recruiters extends ListActivity
{
    private ArrayList<Recuit> m_parts = new ArrayList<>();
    private RecruiterAdapter m_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        m_adapter = new RecruiterAdapter(this, R.layout.recruiter_layout, m_parts);
        setListAdapter(m_adapter);

        Runnable viewParts = new Runnable() {
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
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
            m_parts.add(new Recuit("Zoho Corporation",R.drawable.ra));
            m_parts.add(new Recuit("Amazon",R.drawable.rb));
            m_parts.add(new Recuit("Vuram",R.drawable.rc));
            m_parts.add(new Recuit("Wipro",R.drawable.rd));
            m_parts.add(new Recuit("HP",R.drawable.re));
            m_parts.add(new Recuit("Accenture",R.drawable.rf));
            m_parts.add(new Recuit("Bosch",R.drawable.rg));
            m_parts.add(new Recuit("Soliton",R.drawable.rh));
            m_parts.add(new Recuit("Tech MAhindra",R.drawable.ri));
            m_parts.add(new Recuit("Info View",R.drawable.rj));
            m_parts.add(new Recuit("Oracle",R.drawable.rk));
            m_parts.add(new Recuit("KGISL",R.drawable.rl));
            m_parts.add(new Recuit("Infosys",R.drawable.rm));
            m_parts.add(new Recuit("Cognizant",R.drawable.rn));
            m_parts.add(new Recuit("TCS",R.drawable.ro));

            m_adapter = new RecruiterAdapter(Recruiters.this, R.layout.recruiter_layout, m_parts);

            // display the list.
            setListAdapter(m_adapter);
        }
    };
}
