package com.example.kisha.skct;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.kisha.skct.MainActivity.auth;
import static com.example.kisha.skct.MainActivity.updateUserData;
import static com.example.kisha.skct.MainActivity.userEmail;
import static com.example.kisha.skct.MainActivity.userId;
import static com.example.kisha.skct.MainActivity.userName;
import static com.example.kisha.skct.MainActivity.userPhotoUrl;
import static com.example.kisha.skct.MainActivity.userRollNo;

public class LoginHome extends AppCompatActivity
{
    TextInputEditText email, password;
    String inpRoll, inpPassword;
    Context ctx = this;
    ProgressBar progressBar;
    TextView tv;
    Button button;

    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_home);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean("START_AB", false);
        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean("START_AB", Boolean.TRUE);
            edit.commit();
            startActivity(new Intent(getApplicationContext(), LaunchActivity.class));
            return;
        }


        if (auth != null)
        {
            user = auth.getCurrentUser();
        }

        if (user != null)
        {
            startActivity(new Intent(LoginHome.this, MainActivity.class));
            finish();
        }

        userRollNo = null;
        userEmail = null;
        userId = null;
        userPhotoUrl = null;
        userName = null;

        progressBar = (ProgressBar) findViewById(R.id.progressBar4);
        button = (Button) findViewById(R.id.button43);
        tv = (TextView) findViewById(R.id.textView66);
        email = (TextInputEditText) findViewById(R.id.inputEditText);
        password = (TextInputEditText) findViewById(R.id.inputEditText2);

        progressBar.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_register();
            }
        });

        tv.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {
                                Intent in = new Intent(getApplicationContext(), SignupHome.class);
                                startActivity(in);
                            }
                        }
                );
    }

    @Override
    public void onBackPressed()
    {
        Intent in = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(in);
    }

    public void register_register()
    {
        button.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        inpRoll = email.getText().toString().trim();
        inpPassword = password.getText().toString().trim();

        if (TextUtils.isEmpty(inpRoll))
        {
            email.setError("Empty email");
            progressBar.setVisibility(View.INVISIBLE);
            button.setVisibility(View.VISIBLE);
            return;
        }

        if (TextUtils.isEmpty(inpPassword))
        {
            password.setError("Empty password");
            progressBar.setVisibility(View.INVISIBLE);
            button.setVisibility(View.VISIBLE);
            return;
        }


        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(inpRoll, inpPassword)
                .addOnCompleteListener(LoginHome.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (!task.isSuccessful())
                        {
                            if (inpPassword.length() < 6)
                            {
                                password.setError("Min 6 Chars");
                            }
                            else
                            {
                                Toast.makeText(LoginHome.this, "Try again!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            user = auth.getCurrentUser();
                            if(user.isEmailVerified())
                            {
                                updateUserData();
                                Intent intent = new Intent(LoginHome.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                auth.signOut();
                                Toast.makeText(getApplicationContext(), "Verify your email!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                        button.setVisibility(View.VISIBLE);
                    }
                });
    }


}