package com.example.kisha.skct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CugHome extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cug_home);

        Button b1 = (Button) findViewById(R.id.button22);
        Button b2 = (Button) findViewById(R.id.button23);
        Button b3 = (Button) findViewById(R.id.button24);
        Button b4 = (Button) findViewById(R.id.button25);
        Button b5 = (Button) findViewById(R.id.button26);
        Button b6 = (Button) findViewById(R.id.button27);
        Button b7 = (Button) findViewById(R.id.button28);
        Button b8 = (Button) findViewById(R.id.button29);
        Button b9 = (Button) findViewById(R.id.button30);
        Button b10 = (Button) findViewById(R.id.button31);
        Button b11 = (Button) findViewById(R.id.button32);
        Button b12 = (Button) findViewById(R.id.button33);
        Button b13 = (Button) findViewById(R.id.button34);
        Button b14 = (Button) findViewById(R.id.button35);
        Button b15 = (Button) findViewById(R.id.button36);
        Button b16 = (Button) findViewById(R.id.button37);
        Button b17 = (Button) findViewById(R.id.button38);

        b1.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugAdmin.class);
                                startActivity(in);
                            }
                        }
                );
        b2.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugOffice.class);
                                startActivity(in);
                            }
                        }
                );
        b3.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugCivil.class);
                                startActivity(in);
                            }
                        }
                );

        b4.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugMech.class);
                                startActivity(in);
                            }
                        }
                );
        b5.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugEce.class);
                                startActivity(in);
                            }
                        }
                );
        b6.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugEee.class);
                                startActivity(in);
                            }
                        }
                );
        b7.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugIce.class);
                                startActivity(in);
                            }
                        }
                );
        b8.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugCse.class);
                                startActivity(in);
                            }
                        }
                );

        b9.setOnClickListener
            (
                    new View.OnClickListener()
                    {
                        public void onClick(View v)
                        {
                            Intent in = new Intent(getApplicationContext(), CugIt.class);
                            startActivity(in);
                        }
                    }
            );
        b10.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugSh.class);
                                startActivity(in);
                            }
                        }
                );
        b11.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugMba.class);
                                startActivity(in);
                            }
                        }
                );
        b12.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugMca.class);
                                startActivity(in);
                            }
                        }
                );
        b13.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugPe.class);
                                startActivity(in);
                            }
                        }
                );
        b14.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugPl.class);
                                startActivity(in);
                            }
                        }
                );
        b15.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugSys.class);
                                startActivity(in);
                            }
                        }
                );
        b16.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugLib.class);
                                startActivity(in);
                            }
                        }
                );
        b17.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), CugSupp.class);
                                startActivity(in);
                            }
                        }
                );

    }
}
