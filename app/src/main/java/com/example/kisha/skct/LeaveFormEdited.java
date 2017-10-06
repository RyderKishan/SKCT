package com.example.kisha.skct;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import static com.example.kisha.skct.MainActivity.userEmail;
import static com.example.kisha.skct.MainActivity.userName;
import static com.example.kisha.skct.MainActivity.userRollNo;

public class LeaveFormEdited extends AppCompatActivity
{
    DatePicker datePicker;
    Calendar calendar;
    TextView from_date,name,roll;
    int y, m, d;
    EditText reason, days;
    public Context ctx = this;
    String Name, Roll, Year = "I", Dept = "Mech", Sec = "A", Days, Date, Reason, Odlv, Temp, reference;
    public static ProgressBar progressBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_form_edited);

        button = (Button) findViewById(R.id.button54);
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);

        offProgressVisibility();
        onButtonVisibility();

        from_date = (TextView) findViewById(R.id.textView96);
        calendar = Calendar.getInstance();
        y = calendar.get(Calendar.YEAR);
        m = calendar.get(Calendar.MONTH);
        d = calendar.get(Calendar.DAY_OF_MONTH);


        reason = (EditText) findViewById(R.id.editText9);
        name = (TextView) findViewById(R.id.textView163);
        roll = (TextView) findViewById(R.id.textView164);
        days = (EditText) findViewById(R.id.editText8);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.years,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        Temp = (String) parent.getItemAtPosition(position);
                        Year = Temp;
                    }

                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });



        Spinner spinner1 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.department,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        Temp = (String) parent.getItemAtPosition(position);
                        Dept = Temp;
                    }

                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.section,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        Temp = (String) parent.getItemAtPosition(position);
                        Sec = Temp;
                    }

                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

        name.setText(userName+" - "+userEmail);
        roll.setText(userRollNo);
    }

    public void offButtonVisibility()
    {
        button.setVisibility(View.INVISIBLE);
    }
    public void onButtonVisibility()
    {
        button.setVisibility(View.VISIBLE);
    }
    public void offProgressVisibility()
    {
        progressBar.setVisibility(View.INVISIBLE);
    }
    public void onProgressVisibility()
    {
        progressBar.setVisibility(View.VISIBLE);
    }


    @SuppressWarnings("deprecation")
    public void setD(View view)
    {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        // TODO Auto-generated method stub
        if (id == 999)
        {
            return new DatePickerDialog(this, myDateListener, y, m, d);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener()
            {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3)
                {
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day)
    {
        from_date.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
        Date = from_date.getText().toString();
        Log.e("Date in show date", Date);
    }

    public void onRadioButtonClicked(View view)
    {
        switch(view.getId())
        {
            case R.id.radio_pirates:
            {
                reason.setHint("Reason for On Duty");
                Odlv = "OD";
                break;
            }
            case R.id.radio_ninjas:
            {
                reason.setHint("Reason for Leave");
                Odlv = "LEAVE";
                break;
            }
        }
    }

    public void register_register(View v)
    {
        offButtonVisibility();
        onProgressVisibility();
        Name = userName+"-"+userEmail;
        Roll = userRollNo;
        Days = days.getText().toString().trim();
        Reason = reason.getText().toString().trim();
        BackGround b = new BackGround();
        b.execute(Name, Roll, Year, Dept, Sec, Days, Date, Reason, Odlv);
    }

    class BackGround extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            String name = params[0];
            String roll = params[1];
            String year = params[2];
            String dept = params[3];
            String sec = params[4];
            String days = params[5];
            String date = params[6];
            String reason = params[7];
            String odlv = params[8];

            String data = "";
            int tmp;

            try
            {
                URL url = new URL("http://app1.skct.edu.in:808/skctapp/android_developer_OD_Leave.php");
                String urlParams = "name=" + name + "&roll_no=" + roll + "&year=" + year + "&dept=" + dept + "&session=" + sec + "&days=" + days + "&reason=" + reason + "&date=" + date + "&odlv=" + odlv;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1)
                {
                    data += (char) tmp;
                }
                is.close();
                httpURLConnection.disconnect();
                return data;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }


        @Override
        protected void onPostExecute(String s)
        {
            if (s.startsWith("Exception"))
            {
                Toast.makeText(ctx, "Error! Try again", Toast.LENGTH_LONG).show();
            }
            else
            {
                if (s.startsWith("recieved_"))
                {
                    reference = s.substring(9);
                    Intent i = new Intent(getApplicationContext(), OdDisplay.class);
                    i.putExtra("REFERENCE_ID", reference);
                    startActivity(i);
                }
                else
                {
                    if (s.equals(""))
                    {
                        s = "No response";
                    }
                    Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();

                }

            }
            offProgressVisibility();
            onButtonVisibility();

        }
    }
}

