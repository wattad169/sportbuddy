package com.example.mostafawattad.sportbuddy;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateEventSc extends AppCompatActivity {

    Spinner spn;
    ArrayAdapter<CharSequence>adapter;
    private Typeface myTypeFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_sc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spn = (Spinner) findViewById(R.id.spinner_type);
        adapter = ArrayAdapter.createFromResource(this,R.array.sport_types,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "selected", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Typeface myTypeFace = Typeface.createFromAsset(getAssets(),"fontBtn.ttf");
        TextView titleView = (TextView)findViewById(R.id.titlesc);
        titleView.setTypeface(myTypeFace);


        TextView nameView = (TextView)findViewById(R.id.name_title);
        nameView.setTypeface(myTypeFace);


        TextView typeView = (TextView)findViewById(R.id.type);
        typeView.setTypeface(myTypeFace);

        TextView dateView = (TextView)findViewById(R.id.date_title);
        dateView.setTypeface(myTypeFace);

        Button createBtn = (Button)findViewById(R.id.create_event_btn);
        createBtn.setTypeface(myTypeFace);}



    }

