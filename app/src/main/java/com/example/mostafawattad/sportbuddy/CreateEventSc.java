package com.example.mostafawattad.sportbuddy;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.*;
import java.util.Arrays;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateEventSc extends AppCompatActivity {

    public final static String LOGGEDUSERID = "loggedUserId";
    public final static String EVENTINFORM = "eventInform";
    Intent thisClass;
    String userID;

    Spinner spn;
    ArrayAdapter<CharSequence>adapter;
    private EditText nameTxt;
    private Spinner typeTxt;
    private TextView dateTxt;
    private TextView timeTxt;
    private EditText locationTxt;
    private EditText membersTxt;

    final Calendar c = Calendar.getInstance();
    /*change to dictionary ???? */
    private String[] detalis = new String[7]; /*idUser,name,type,date,time,location,members*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_sc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        thisClass = getIntent();
        userID = thisClass.getStringExtra(LOGGEDUSERID);

        dateTxt = (TextView) findViewById(R.id.dateText);
        dateTxt.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

        timeTxt = (TextView) findViewById(R.id.timeText);
        timeTxt.setText(new SimpleDateFormat("hh:mm a").format(new Date()));


        initFonts();
        initSniperType();

    }

    DatePickerDialog.OnDateSetListener date= new  DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker v,int year,int month,int day)
        {
            c.set(Calendar.YEAR,year);
            c.set(Calendar.MONTH,month);
            c.set(Calendar.DAY_OF_MONTH,day);
            setCurrentDataOnView();
        }
    };

    public void onDateClick(View v)
    {
        new DatePickerDialog(CreateEventSc.this,date,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
    }


    TimePickerDialog.OnTimeSetListener time = new  TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker v,int hourOfDay,int minutes)
        {
            c.set(Calendar.HOUR,hourOfDay);
            c.set(Calendar.MINUTE,minutes);
            setCurrentDataOnView();
        }
    };

    public void onTimeClick(View v)
    {
        new TimePickerDialog(CreateEventSc.this,time,c.get(Calendar.HOUR),c.get(Calendar.MINUTE),false).show();
    }



    public void setCurrentDataOnView()
    {
        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        dateTxt.setText(sdf.format(c.getTime()));

        String timeFormat = "hh:mm a";
        SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.US);
        timeTxt.setText(stf.format(c.getTime()));
    }
    private void initSniperType()
    {
        typeTxt = (Spinner) findViewById(R.id.spinner_type);
        adapter = ArrayAdapter.createFromResource(this,R.array.sport_types,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeTxt.setAdapter(adapter);
        typeTxt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getBaseContext(), "", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void initFonts()
    {
        Typeface myTypeFace = Typeface.createFromAsset(getAssets(),"fontBtn.ttf");
        TextView titleView = (TextView)findViewById(R.id.titlesc);
        titleView.setTypeface(myTypeFace);


        TextView nameView = (TextView)findViewById(R.id.name_title);
        nameView.setTypeface(myTypeFace);

        TextView typeView = (TextView)findViewById(R.id.type);
        typeView.setTypeface(myTypeFace);

        TextView dateView = (TextView)findViewById(R.id.date_title);
        dateView.setTypeface(myTypeFace);

    }

    public void CreateOnClick(View v)
    {
        /*idUser,name,type,date,time,location,members*/

        detalis[0] = userID;

        nameTxt = (EditText)findViewById(R.id.nameTxt);
        detalis[1] = nameTxt.getText().toString();

        detalis[2] = typeTxt.getSelectedItem().toString();

        detalis[3] = dateTxt.getText().toString();

        detalis[4] = timeTxt.getText().toString();

        locationTxt = (EditText)findViewById(R.id.locTxt);
        detalis[5] = locationTxt.getText().toString();

        membersTxt = (EditText)findViewById(R.id.membersTxt);
        detalis[6] = String.valueOf(membersTxt.getText());


        Intent intent = new Intent(getApplicationContext(),EventInfo.class);
        intent.putExtra(EVENTINFORM,detalis);
        intent.putExtra(LOGGEDUSERID,userID);
        startActivity(intent);
    }
}
