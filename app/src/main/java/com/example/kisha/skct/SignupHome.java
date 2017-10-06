package com.example.kisha.skct;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import static com.example.kisha.skct.MainActivity.auth;
import static com.example.kisha.skct.MainActivity.userRollNo;

public class SignupHome extends AppCompatActivity
{

    TextInputEditText name, password, email, rollNo;
    String inpName, inpPassword, inpEmail, inpRollNo;
    FirebaseUser user;
    Context ctx = this;
    ProgressBar progressBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_home);

        user = auth.getCurrentUser();

        progressBar = (ProgressBar) findViewById(R.id.progressBar5);
        button = (Button) findViewById(R.id.button44);

        progressBar.setVisibility(View.INVISIBLE);

        name = (TextInputEditText) findViewById(R.id.inputEditText3);
        password = (TextInputEditText) findViewById(R.id.inputEditText5);
        email = (TextInputEditText) findViewById(R.id.inputEditText4);
        rollNo = (TextInputEditText) findViewById(R.id.inputEditText6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void createUser()
    {

        inpName = name.getText().toString().trim();
        inpPassword = password.getText().toString().trim();
        inpEmail = email.getText().toString().trim();
        inpRollNo = rollNo.getText().toString().trim().toLowerCase();

        progressBar.setVisibility(View.VISIBLE);
        button.setVisibility(View.INVISIBLE);

        if (TextUtils.isEmpty(inpName))
        {
            name.setError("Empty name");
            button.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        if (TextUtils.isEmpty(inpEmail))
        {
            email.setError("Empty email");
            button.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        if (TextUtils.isEmpty(inpPassword))
        {
            password.setError("Empty password");
            button.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        if (TextUtils.isEmpty(inpRollNo))
        {
            password.setError("Empty roll no");
            button.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        if (!inpEmail.endsWith("@skct.edu.in"))
        {
            email.setError("Use SKCT domain");
            button.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        if (inpPassword.length() < 6)
        {
            password.setError("Minimum 6 characters required");
            button.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        if (inpRollNo.length() < 4)
        {
            password.setError("Roll no invalid");
            button.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }

        auth.createUserWithEmailAndPassword(inpEmail, inpPassword)
                .addOnCompleteListener(SignupHome.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        if (!task.isSuccessful())
                        {
                            Toast.makeText(SignupHome.this, "Failed!", Toast.LENGTH_SHORT).show();
                            button.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            user = auth.getCurrentUser();
                            if (user != null)
                            {
                                if (!user.isEmailVerified())
                                {
                                    user.sendEmailVerification();
                                }
                            }

                            Toast.makeText(SignupHome.this, "Verify email!", Toast.LENGTH_SHORT).show();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(inpName+"%"+inpRollNo)
                                    .setPhotoUri(Uri.parse("https://firebasestorage.googleapis.com/v0/b/skct-1b2d0.appspot.com/o/profilepics%2Fdefault.jpg?alt=media&token=0b4b65b2-8c39-479f-ae53-b6df27760d89"))
                                    .build();
                            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        userRollNo = inpRollNo;
                                        auth.signOut();
                                    }
                                }
                            });

                            startActivity(new Intent(SignupHome.this, LoginHome.class));
                            finish();
                        }
                    }
                });

    }
}
