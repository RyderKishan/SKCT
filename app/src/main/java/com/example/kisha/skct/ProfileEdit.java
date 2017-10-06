package com.example.kisha.skct;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import static com.example.kisha.skct.MainActivity.auth;
import static com.example.kisha.skct.MainActivity.userEmail;
import static com.example.kisha.skct.MainActivity.userName;
import static com.example.kisha.skct.MainActivity.userRollNo;

public class ProfileEdit extends AppCompatActivity
{
    TextInputEditText name, email, password, rollno;
    String inpName, inpEmail, inpPassword, inpRollNo;
    FirebaseUser user;
    ProgressBar progressBar;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        user = auth.getCurrentUser();

        name = (TextInputEditText) findViewById(R.id.inputEditText7);
        email = (TextInputEditText) findViewById(R.id.inputEditText8);
        password = (TextInputEditText) findViewById(R.id.inputEditText9);
        rollno = (TextInputEditText) findViewById(R.id.inputEditText11);
        update = (Button) findViewById(R.id.button63);
        progressBar = (ProgressBar) findViewById(R.id.progressBar12);

        name.setText(userName);
        email.setText(userEmail);
        rollno.setText(userRollNo);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                inpName = name.getText().toString();
                inpEmail = email.getText().toString();
                inpPassword = password.getText().toString();
                inpRollNo = rollno.getText().toString();



                if((!inpName.equals("") && inpName!=null) && !(inpName.equals(userName)))
                {
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(inpName+"%"+userRollNo)
                            .build();
                    user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                userName = inpName;
                                Toast.makeText(ProfileEdit.this, "Name Changed!", Toast.LENGTH_SHORT).show();
                                name.setText("");
                            }
                        }
                    });
                }

                if((!inpEmail.equals("") && inpEmail!=null) && !(inpEmail.equals(user.getEmail())))
                {
                    user.updateEmail(inpEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(ProfileEdit.this, "Mail Changed! - verify new mail!", Toast.LENGTH_SHORT).show();
                                email.setText("");
                            }
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        auth.signOut();
                                        Toast.makeText(ProfileEdit.this, "Login with new mail!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
                }

                if((!inpRollNo.equals("") && inpRollNo!=null) && !(inpRollNo.equals(userRollNo)))
                {
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(userName+"%"+inpRollNo)
                            .build();
                    user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                userRollNo = inpRollNo;
                                Toast.makeText(ProfileEdit.this, "Roll Changed!", Toast.LENGTH_SHORT).show();
                                Toast.makeText(ProfileEdit.this, "Please note - your changes are recorded with your email id!", Toast.LENGTH_SHORT).show();
                                name.setText("");
                            }
                        }
                    });
                }


                if(!inpPassword.equals("") && inpPassword!=null)
                {
                    user.updatePassword(inpPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(ProfileEdit.this, "Password Changed - Login Again!", Toast.LENGTH_SHORT).show();
                                password.setText("");
                                auth.signOut();
                                startActivity(new Intent(ProfileEdit.this, MainActivity.class));
                            }
                        }
                    });
                }
                else
                    Toast.makeText(ProfileEdit.this, "Can`t change password!", Toast.LENGTH_SHORT).show();

                progressBar.setVisibility(View.INVISIBLE);
                update.setVisibility(View.VISIBLE);

            }
        });



    }
}
