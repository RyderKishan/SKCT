package com.example.kisha.skct;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.multidex.MultiDex;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    TextView tv,textView;
    Context ctx = this;
    String verName;

    public static String userName, userEmail, userId, userRollNo;
    public static Uri userPhotoUrl;

    FirebaseAuth.AuthStateListener authListener;
    public static FirebaseAuth auth;
    FirebaseUser user;

    int imageFlag = 0;
    public static int flag = 0;
    ImageView imageView1 , imageView2, imageView;

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();



        if (user == null)
            startActivity(new Intent(getApplicationContext(), LoginHome.class));

        authListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                user = firebaseAuth.getCurrentUser();
                if (user == null)
                {
                    startActivity(new Intent(MainActivity.this, LoginHome.class));
                }
                else
                {
                    updateUserData();
                }
            }
        };
        updateUserData();




        textView = (TextView) findViewById(R.id.textView94);

        try
        {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            verName = packageInfo.versionName;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        textView.setText(getResources().getText(R.string.copy) + " v " + verName);

        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null)
        {
            Toast.makeText(ctx, "No internet connection!", Toast.LENGTH_SHORT).show();
        }


        tv = (TextView) findViewById(R.id.textView118);
        tv.setText("Welcome "+ userName +"!");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final int[] resource = new int[]{R.drawable.ma, R.drawable.mb, R.drawable.mc, R.drawable.md, R.drawable.me, R.drawable.mf, R.drawable.mg, R.drawable.mh, R.drawable.mi, R.drawable.mj, R.drawable.mk, R.drawable.ml};

        imageView1 = (ImageView) findViewById(R.id.imageView48);
        imageView2 = (ImageView) findViewById(R.id.imageView49);
        imageView = (ImageView) findViewById(R.id.imageView47);


        imageView1.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                if(imageFlag > 0)
                                {
                                    imageView.setImageResource(resource[(--imageFlag)]);
                                }
                                else
                                {
                                    Toast.makeText(ctx, "Go Right", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                );

        imageView2.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                if(imageFlag < 11)
                                {
                                    imageView.setImageResource(resource[(++imageFlag)]);
                                }
                                else
                                {
                                    Toast.makeText(ctx, "Go Left", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                );
    }

    public static void updateUserData()
    {
        if (auth != null && auth.getCurrentUser() != null)
        {
            try
            {
                String temp = auth.getCurrentUser().getDisplayName();
                userName = temp.substring(0, temp.indexOf('%'));
                userRollNo = temp.substring(temp.indexOf('%')+1);
                Log.e("USER + Roll : ", userName+" - "+userRollNo);
                userEmail = auth.getCurrentUser().getEmail();
                userPhotoUrl = auth.getCurrentUser().getPhotoUrl();
                userId = auth.getCurrentUser().getUid();
            }
            catch(Exception e)
            {
                Log.e("Main : ",e.getMessage());
            }
        }

    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Are you sure to exit?");
            alertDialogBuilder
                    .setMessage("Click yes to exit!")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    moveTaskToBack(true);
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            })

                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login)
        {
            flag = 0;
            if (user == null)
            {
                startActivity(new Intent(MainActivity.this, LoginHome.class));
            }
            else
            {
                startActivity(new Intent(MainActivity.this, Divert.class));
            }
        }
        else if (id == R.id.nav_chat)
        {
            startActivity(new Intent(getApplicationContext(), ChatActivity.class));
        }
        else if (id == R.id.nav_departments)
        {
            Intent i = new Intent(getApplicationContext(), DepartmentHome.class);
            startActivity(i);
        }
        else if (id == R.id.nav_placements)
        {
            Intent i = new Intent(MainActivity.this, PlacementHome.class);
            startActivity(i);
        }

        else if (id == R.id.nav_facilities)
        {
            Intent i = new Intent(MainActivity.this, FacilitiesHome.class);
            startActivity(i);
        }
        else if (id == R.id.nav_cug)
        {
            flag = 1;
            if (user == null)
            {
                startActivity(new Intent(MainActivity.this, LoginHome.class));
            }
            else
            {
                startActivity(new Intent(MainActivity.this, CugHome.class));
            }
        }
        else if (id == R.id.nav_studentszone)
        {
            Intent i = new Intent(MainActivity.this, StudentsZoneHome.class);
            startActivity(i);
        }
        else if (id == R.id.nav_skctnews)
        {
            Intent i = new Intent(MainActivity.this, SkctNewsEdited.class);
            startActivity(i);
        }
        else if (id == R.id.nav_skctcalendar)
        {
            Intent i = new Intent(MainActivity.this, CalendarDisplay.class);
            startActivity(i);
        }
        else if (id == R.id.nav_monthlyreport)
        {
            Intent i = new Intent(MainActivity.this, MonthlyReportHome.class);
            startActivity(i);
        }
        else if (id == R.id.nav_admission)
        {
            Intent i = new Intent(MainActivity.this, Admission.class);
            startActivity(i);
        }
        else if (id == R.id.nav_forms)
        {
            flag = 2;
            if (user == null)
            {
                startActivity(new Intent(MainActivity.this, LoginHome.class));
            }
            else
            {
                startActivity(new Intent(MainActivity.this, FormsHome.class));
            }
        }
        else if (id == R.id.nav_gallery)
        {
            Intent i = new Intent(MainActivity.this, ZGalleryHome.class);
            startActivity(i);
        }
        else if (id == R.id.nav_aboutus)
        {
            Intent i = new Intent(MainActivity.this, ManagementHome.class);
            startActivity(i);
        }
        else if (id == R.id.nav_contactus)
        {
            startActivity(new Intent(MainActivity.this, ContactUsHome.class));
        }

        else if (id == R.id.nav_share)
        {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, "Hai guys i`ve been using SKCT APP! and it looks quite awesome If you would like to try it please? - http://app1.skct.edu.in:808/skctapp/update/skctapp.apk ");
            if(i.resolveActivity(getPackageManager()) != null)
            {
                startActivity(i);
            }
        }

        else if (id == R.id.nav_developers)
        {
            startActivity(new Intent(getApplicationContext(), DeveloperName.class));
        }

        else if (id == R.id.nav_feedback)
        {
            startActivity(new Intent(MainActivity.this, FeedbackHome.class));
        }

        else if (id == R.id.nav_update)
        {
            startActivity(new Intent(MainActivity.this, UpdateHome.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}