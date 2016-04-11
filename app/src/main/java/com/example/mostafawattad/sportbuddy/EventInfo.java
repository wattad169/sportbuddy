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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EventInfo extends AppCompatActivity {

    public final static String LOGGEDUSERID = "loggedUserId";
    public final static String EVENTINFORM = "eventInform";
    Intent thisClass;
    String userID;
    private String[] detalis; /*idUser,name,type,date,time,location,members*/
    EditText NameEvenet;
    EditText TypeEvent;
    EditText DateEvent;
    EditText TimeEvent;
    EditText LocationEvent;
    EditText MembersEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initFonts();

        thisClass = getIntent();
        userID = thisClass.getStringExtra(LOGGEDUSERID);
        detalis = thisClass.getStringArrayExtra(EVENTINFORM);

        NameEvenet = (EditText) findViewById(R.id.Event_title);
        NameEvenet.setText(detalis[1]);

        TypeEvent = (EditText) findViewById(R.id.typeTxt);
        TypeEvent.setText(detalis[2]);

        DateEvent = (EditText) findViewById(R.id.dateTxt);
        DateEvent.setText(detalis[3]);

        TimeEvent = (EditText) findViewById(R.id.timeTxt);
        TimeEvent.setText(detalis[4]);

        LocationEvent = (EditText) findViewById(R.id.locationTxt);
        LocationEvent.setText(detalis[5]);

        MembersEvent = (EditText) findViewById(R.id.membersTxt);
        MembersEvent.setText(detalis[6]);

        Button EditBrn = (Button) findViewById(R.id.Edit_btn);
        if(userID != detalis[0])  EditBrn.setVisibility(Button.INVISIBLE);

    }
    private void initFonts()
    {
        Typeface myTypeFace = Typeface.createFromAsset(getAssets(),"fontBtn.ttf");

        TextView typeTitle = (TextView)findViewById(R.id.Type_title);
        typeTitle.setTypeface(myTypeFace);

        TextView dateTitle = (TextView)findViewById(R.id.Date_title);
        dateTitle.setTypeface(myTypeFace);

        TextView timeTitle = (TextView)findViewById(R.id.Time_title);
        timeTitle.setTypeface(myTypeFace);

        TextView locationTitle = (TextView)findViewById(R.id.Location_title);
        locationTitle.setTypeface(myTypeFace);

        TextView membersTitle = (TextView)findViewById(R.id.Members_title);
        membersTitle.setTypeface(myTypeFace);

    }


    public void saveOnClick(View v)
    {
        detalis[0] = userID;


        detalis[1] = NameEvenet.getText().toString();

        detalis[2] = TypeEvent.getText().toString();

        detalis[3] = DateEvent.getText().toString();

        detalis[4] = TimeEvent.getText().toString();


        detalis[5] = LocationEvent.getText().toString();


        detalis[6] = String.valueOf(MembersEvent.getText());

    }

    public void editOnClick(View v)
    {
        NameEvenet.setFocusable(true);

        TypeEvent.setFocusable(true);

        DateEvent.setFocusable(true);

        TimeEvent.setFocusable(true);

        LocationEvent.setFocusable(true);

        MembersEvent.setFocusable(true);
    }

}
