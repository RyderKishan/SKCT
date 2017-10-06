package com.example.kisha.skct;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static com.example.kisha.skct.MainActivity.auth;
import static com.example.kisha.skct.MainActivity.userEmail;
import static com.example.kisha.skct.MainActivity.userName;
import static com.example.kisha.skct.MainActivity.userPhotoUrl;

public class Divert extends AppCompatActivity
{
    Button b1,delete;
    TextView tv;
    FloatingActionButton floatingActionButton;
    ProgressBar progressBar, progressBar2;
    TextInputEditText userInput;
    String inpPass;
    ImageView profilePic;
    FirebaseUser user;
    Context context = this;
    ProgressDialog progressDialog;
    StorageReference storageReference;
    private static int GALLERY_INTENT = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divert);

        user = auth.getCurrentUser();

        b1 = (Button) findViewById(R.id.button47);
        delete = (Button) findViewById(R.id.button64);
        tv = (TextView) findViewById(R.id.textView101);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        progressBar = (ProgressBar) findViewById(R.id.progressBar14);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar13);
        profilePic = (ImageView) findViewById(R.id.imageView61);
        progressBar.setVisibility(View.INVISIBLE);
        progressBar2.setVisibility(View.INVISIBLE);

        storageReference = FirebaseStorage.getInstance().getReference();
        progressDialog = new ProgressDialog(Divert.this);

        Picasso.with(Divert.this)
                .load(userPhotoUrl)
                .error(R.drawable.aa)
                .resize(150,150)
                .centerCrop()
                .into(profilePic);

        tv.setText(userName);

        b1.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            public void onClick(View v)
                            {

                                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                                boolean previouslyStarted = prefs.getBoolean("START_AB", false);
                                SharedPreferences.Editor edit = prefs.edit();
                                edit.putBoolean("START_AB", Boolean.FALSE);
                                edit.commit();
                                auth.signOut();
                                Intent in3 = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(in3);
                            }
                        }
                );


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                progressBar.setVisibility(View.VISIBLE);
                floatingActionButton.setVisibility(View.INVISIBLE);
                LayoutInflater li = LayoutInflater.from(context);
                final View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setView(promptsView);

                userInput = (TextInputEditText) promptsView.findViewById(R.id.inputEditText10);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Submit",
                                new DialogInterface.OnClickListener()
                                {
                                    public void onClick(final DialogInterface dialog, int id)
                                    {
                                        // get user input and set it to result
                                        // edit text
                                        inpPass = userInput.getText().toString();
                                        AuthCredential credential = EmailAuthProvider.getCredential(userEmail, inpPass);



                                        user.reauthenticate(credential)
                                                .addOnCompleteListener(new OnCompleteListener<Void>()
                                                {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task)
                                                    {
                                                        if (task.isSuccessful())
                                                        {
                                                            progressBar.setVisibility(View.INVISIBLE);
                                                            floatingActionButton.setVisibility(View.VISIBLE);
                                                            startActivity(new Intent(Divert.this, ProfileEdit.class));
                                                        }
                                                        else
                                                        {
                                                            progressBar.setVisibility(View.INVISIBLE);
                                                            floatingActionButton.setVisibility(View.VISIBLE);
                                                            Toast.makeText(context, "Try Again!", Toast.LENGTH_SHORT).show();
                                                            dialog.cancel();
                                                        }

                                                    }
                                                });
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        floatingActionButton.setVisibility(View.VISIBLE);
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar2.setVisibility(View.VISIBLE);
                delete.setVisibility(View.INVISIBLE);
                LayoutInflater li = LayoutInflater.from(context);
                final View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setView(promptsView);

                userInput = (TextInputEditText) promptsView.findViewById(R.id.inputEditText10);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Submit",
                                new DialogInterface.OnClickListener()
                                {
                                    public void onClick(final DialogInterface dialog, int id)
                                    {
                                        // get user input and set it to result
                                        // edit text
                                        inpPass = userInput.getText().toString().trim();

                                        AuthCredential credential = EmailAuthProvider.getCredential(userEmail, inpPass);



                                        user.reauthenticate(credential)
                                                .addOnCompleteListener(new OnCompleteListener<Void>()
                                                {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task)
                                                    {
                                                        if (task.isSuccessful())
                                                        {
                                                            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful())
                                                                    {
                                                                        auth.signOut();
                                                                        progressBar2.setVisibility(View.INVISIBLE);
                                                                        delete.setVisibility(View.VISIBLE);
                                                                        startActivity(new Intent(Divert.this, LoginHome.class));
                                                                    }
                                                                    else
                                                                    {
                                                                        Toast.makeText(context, "Delete failed!", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                            });

                                                        }
                                                        else
                                                        {
                                                            progressBar2.setVisibility(View.INVISIBLE);
                                                            delete.setVisibility(View.VISIBLE);
                                                            Toast.makeText(context, "Can`t reach server!", Toast.LENGTH_SHORT).show();
                                                            dialog.cancel();
                                                        }

                                                    }
                                                });

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        progressBar2.setVisibility(View.INVISIBLE);
                                        delete.setVisibility(View.VISIBLE);
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK && data != null)
        {
            progressDialog.setMessage("Uploading...");
            progressDialog.setTitle("Profile Pic Uploading");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setCancelable(true);
            progressDialog.show();
            Uri uri = data.getData();

            StorageReference filePath = storageReference.child("profilepics").child(user.getUid());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.e("Uploaded to : ",taskSnapshot.getDownloadUrl().toString());
                    userPhotoUrl = taskSnapshot.getDownloadUrl();

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setPhotoUri(userPhotoUrl)
                            .build();
                    user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Log.e("Url updated!"," : Success");
                                Picasso.with(Divert.this)
                                        .load(userPhotoUrl)
                                        .error(R.drawable.aa)
                                        .resize(150,150)
                                        .centerCrop()
                                        .into(profilePic);

                            }
                            else
                            {
                                Log.e("Url fail!"," : waste");
                            }
                        }
                    });
                    progressDialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Upload Failed!", Toast.LENGTH_SHORT).show();
                    Log.e("Exception : ", e.getMessage());

                }
            });


        }

    }
}
