package com.example.kisha.skct;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

import static com.example.kisha.skct.MainActivity.userName;

public class ChatActivity extends AppCompatActivity
{
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private FirebaseUser user;

    private final int REQ_CODE_SPEECH_INPUT = 100;

    FirebaseListAdapter<ChatMessage> adapter;
    TextInputEditText input;
    FloatingActionButton fab, voice;
    FirebaseDatabase db;
    DatabaseReference reference;
    ListView listOfMessages;
    Vibrator mVibrator;

    public static boolean calledAlready = false;

    String link = "https://firebasestorage.googleapis.com/v0/b/skct-1b2d0.appspot.com/o/profilepics%2F";
    String token = "?alt=media&token=3ef00390-37f3-4a9b-8113-594361e9494b";

    private void scrollMyListViewToBottom()
    {
        listOfMessages.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                listOfMessages.setSelection(adapter.getCount() - 1);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if (!calledAlready)
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledAlready = true;
        }
        final Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null)
        {
            Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_SHORT).show();
        }

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        displayChat();

        if (getIntent().getExtras() != null)
        {
            for (String key : getIntent().getExtras().keySet())
            {
                Object value = getIntent().getExtras().get(key);
                Log.e("MainActivity", "Key: " + key + " Value: " + value);
            }
        }
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseDatabase.getInstance();
        reference = db.getReference();

        reference.child("Chat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                mVibrator.vibrate(50);

                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0 /* Request code */, intent,
                        PendingIntent.FLAG_ONE_SHOT);

                Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("SKCT Chat")
                        .setContentText("You have new messages!")
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton5);
        voice = (FloatingActionButton) findViewById(R.id.floatingActionButton6);
        input = (TextInputEditText) findViewById(R.id.inputEditText12);




        authListener = new FirebaseAuth.AuthStateListener()
        {



            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                user = firebaseAuth.getCurrentUser();
                if (user == null)
                {
                    startActivity(new Intent(ChatActivity.this, MainActivity.class));
                }
            }
        };



        voice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                promptSpeechInput();

            }
        });



        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (input.getText().toString().trim() == null || input.getText().toString().equals(""))
                {
                    input.setError("Empty Message");
                }
                else
                {

                    reference.child("Chat").push().setValue(new ChatMessage(input.getText().toString(), userName, link+user.getUid()+token));
                    input.setText("");
                    displayChat();
                }

            }
        });

    }

    void displayChat()
    {
        listOfMessages = (ListView)findViewById(R.id.list_of_messages);

        DatabaseReference scoresRef = FirebaseDatabase.getInstance().getReference("chats");
        scoresRef.keepSynced(true);


        String message = FirebaseDatabase.getInstance().getReference().child("Chat").getRoot().toString();


        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference().child("Chat"))
        {
            @Override
            protected void populateView(View v, final ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                messageUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(), DisplayProfile.class);
                        intent.putExtra("DISPLAY_NAME", model.getMessageUser());
                        intent.putExtra("PROFILE_PIC", model.getProfilePic());
                        startActivity(intent);
                    }
                });

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
        scrollMyListViewToBottom();
    }

    private void promptSpeechInput()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now!");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Not Supported",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case REQ_CODE_SPEECH_INPUT:
            {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    input.setText(result.get(0));
                }
                break;
            }

        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_chat, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_chat_timeline:
            {
                Toast.makeText(getApplicationContext(), "Timeline under development!", Toast.LENGTH_SHORT).show();
            }
            return true;
            case R.id.action_chat_profile:
            {
                startActivity(new Intent(ChatActivity.this, Divert.class));
            }
            return true;
            case R.id.action_chat_logout:
            {
                auth.signOut();
            }
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
