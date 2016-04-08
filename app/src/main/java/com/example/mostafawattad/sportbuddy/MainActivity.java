package com.example.mostafawattad.sportbuddy;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    /* change buttons font text - lina */
        Typeface myTypeFace = Typeface.createFromAsset(getAssets(),"fontBtn.ttf");
        Button joinBtn = (Button)findViewById(R.id.join_btn);
        joinBtn.setTypeface(myTypeFace);
        Button createBtn = (Button)findViewById(R.id.create_btn);
        createBtn.setTypeface(myTypeFace);
        Button myGroupsBtn = (Button)findViewById(R.id.myGroups_btn);
        myGroupsBtn.setTypeface(myTypeFace);
    }


    public void joinClick(View v)
    {
        Uri uri = Uri.parse("http://www.google.com.kh");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        startActivity(intent);
    }

    public void createClick(View v)
    {
        startActivity(new Intent(getApplicationContext(), CreateEventSc.class) );
    }

    public void myGroupsClick(View v)
    {
//        Uri uri = Uri.parse("http://www.walla.com");
        Intent intent = new Intent(getApplicationContext(),myEventsActivity.class);
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
