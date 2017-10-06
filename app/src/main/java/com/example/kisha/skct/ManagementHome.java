package com.example.kisha.skct;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class ManagementHome extends AppCompatActivity
{
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managemant_home);

        viewPager = (ViewPager) findViewById(R.id.view_pager3);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(),getApplicationContext()));

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class CustomAdapter extends FragmentPagerAdapter
    {
        private String fragments[] = {"Chairperson and Managing Trustee","Trustee","Advisor","Chief Executive Officer (CEO)","Principal"};

        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext)
        {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    return new Fragment1();
                case 1:
                    return new Fragment2();
                case 2:
                    return new Fragment3();
                case 3:
                    return new Fragment4();
                case 4:
                    return new Fragment5();
                default:
                    return null;
            }
        }

        @Override
        public int getCount()
        {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return fragments[position];
        }
    }
}
