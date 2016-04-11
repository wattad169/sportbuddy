package com.example.mostafawattad.sportbuddy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mostafawattad.sportbuddy.client.NetworkUtilities;

import org.json.JSONException;
import org.json.JSONObject;

public class EventInfo extends AppCompatActivity {

    public final static String LOGGEDUSERID = "loggedUserId";
    public final static String EVENTINFORM = "eventInform";
    Intent thisClass;
    private handleEventTask myEventsTask = null;
    private String userID;
    private String[] detalis; /*idUser,name,type,date,time,location,members*/
    EditText NameEvenet;
    EditText TypeEvent;
    EditText DateEvent;
    EditText TimeEvent;
    EditText LocationEvent;
    EditText MembersEvent;
    Button saveJoinBtn ;
    private String TAG = "EventInfo";
    private int parentMode;
    private String event_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initFonts();

        saveJoinBtn = (Button) findViewById(R.id.Save_btn);
        thisClass = getIntent();
        //inputs
        userID = thisClass.getStringExtra(LOGGEDUSERID);
        detalis = thisClass.getStringArrayExtra(EVENTINFORM);
        parentMode = thisClass.getIntExtra(Constants.parentActivityMode, 1);
        if(parentMode == Constants.joinParentMode){
            event_id = thisClass.getStringExtra(Constants.EVENT_ID);
            saveJoinBtn.setText("Join");

        }
        if(parentMode == Constants.nonParentMode){
            saveJoinBtn.setVisibility(Button.INVISIBLE);
        }



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

        myEventsTask = new handleEventTask(this);
        myEventsTask.execute((Void) null);

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent next = new Intent(getApplication(),MainActivity.class);
        next.putExtra(Constants.LOGGEDUSERID,userID);
        startActivity(next);
        finish();
    }


    public class handleEventTask extends AsyncTask<Void, Void, String> {

        //        private Context context;
        private Context context;
        public handleEventTask(EventInfo activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        /** progress dialog to show user that the backup is processing. */
        private ProgressDialog dialog;
        /** application context. */
        private EventInfo activity;
        private String responseString;
        protected void onPreExecute() {
            this.dialog.setMessage("Handling your request..");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

                Log.i(TAG, "");
                JSONObject cred = new JSONObject();

                try {

                    JSONObject innerCred = new JSONObject();
                    innerCred.put(Constants.LOCATION_LON, 32.113378); //TODO : Fetch from GPS
                    innerCred.put(Constants.LOCATION_LAT, 34.804860);

                    cred.put(Constants.EVENT_LOCATION_CORD, innerCred);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (parentMode == Constants.createParentMode) {
                    try {
                        cred.put(NetworkUtilities.TOKEN, userID);
                    } catch (JSONException e) {
                        Log.i(TAG, e.toString());
                    }
                    try {
                        cred.put(Constants.EVENT_NAME, detalis[1]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        cred.put(Constants.EVENT_TYPE, detalis[2]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        cred.put(Constants.EVENT_DATE, detalis[3]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "CreateEvent message to send :" + cred.toString());
                    responseString = NetworkUtilities.doPost(cred, NetworkUtilities.BASE_URL + "/create_event/");
                } else {
                    try {
                        cred.put(NetworkUtilities.TOKEN, userID);
                    } catch (JSONException e) {
                        Log.i(TAG, e.toString());
                    }
                    try {
                        cred.put(Constants.EVENT_ID, event_id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "JointEvent message to send :" + cred.toString());
                    responseString = NetworkUtilities.doPost(cred, NetworkUtilities.BASE_URL + "/join_event/");
                }

                JSONObject myObject = null;
                String responseStatus = null;
                try {
                    myObject = new JSONObject(responseString);
                    responseStatus = myObject.getString(Constants.RESPONSE_STATUS);
                } catch (JSONException e) {
                    Log.i(TAG, e.toString());
                    e.printStackTrace();
                }
                Log.i(TAG,"Received: " + responseString);
                if (myObject != null && responseStatus != null) {
                    if (responseStatus.equals(Constants.RESPONSE_OK.toString())) {
                        myEventsTask = null;
                        dialog.cancel();
//                        Toast.makeText(context, "Done!", Toast.LENGTH_LONG).show();

                    } else {
                        //User or Password doesn't match .. Forgot password?
//                    mAuthTask = null;
//                    showProgress(false);
                        myEventsTask = null;
                        dialog.cancel();
//                        Toast.makeText(context, "Failed!", Toast.LENGTH_LONG).show();

                        Log.i(TAG, "Failed");
                    }

                }
            return null;
        }


        @Override
        protected void onPostExecute(final String responseString) {



        }




    }
}
