package com.example.mostafawattad.sportbuddy;

/**
 * Created by mostafawattad on 07/04/2016.
 */


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.os.AsyncTask;
import de.codecrafters.tableview.listeners.TableDataClickListener;

import com.example.mostafawattad.sportbuddy.client.NetworkUtilities;
import com.example.mostafawattad.sportbuddy.data.event;
import com.example.mostafawattad.sportbuddy.data.EventType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class myEventsActivity extends AppCompatActivity {
    private static final String TAG = "myEventsActivity: ";
    private getUserEventsTask myEventsTask = null;
    private int receivedEventsMode ;
    private String user_token;
    private String user_id;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevents);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        Intent prev  = getIntent();
        receivedEventsMode = prev.getIntExtra(Constants.eventsMode,1);
        user_token = prev.getStringExtra(Constants.LOGGEDUSERID);
        user_id    = user_token;
        myEventsTask = new getUserEventsTask(this);
        myEventsTask.execute((Void) null);


    }



    private static List<event> createAndGiveCarList(JSONArray inputJson) throws JSONException {
        final List<event> events = new ArrayList<>();
        for (int i=0; i < inputJson.length() ; i++){
            JSONObject currentEvent = (JSONObject) inputJson.get(i);
            String event_type = currentEvent.getString(Constants.EVENT_TYPE).toLowerCase();
            String event_name = currentEvent.getString(Constants.EVENT_NAME);
            String event_date = currentEvent.getString(Constants.EVENT_DATE);
            String event_location = currentEvent.getString(Constants.EVENT_LOCATION);
            String event_id = currentEvent.getString(Constants.ID);
            int event_size = currentEvent.getJSONArray(Constants.EVENT_MEMBERS).length();

            Log.i(TAG, event_type);
            final EventType type = new EventType(Constants.sportTypeToLogo.get(event_type), event_type);
            final event current_event = new event(type,event_name ,event_date,event_location,event_size, 150, 25000,event_id);
            events.add(current_event);
            }


        return events;
    }


    private class CarClickListener implements TableDataClickListener<event> {
        private String[] detalis = new String[7]; /*idUser,name,type,date,time,location,members*/


        @Override
        public void onDataClicked(final int rowIndex, final event clickedData) {
            final String carString = clickedData.getType().getName() + " " + clickedData.getName();


            detalis[0] = user_id;


            detalis[1] = clickedData.getName();

            detalis[2] = clickedData.getType().getName();

            detalis[3] = clickedData.getDate();

            detalis[4] = "";

            detalis[5] = clickedData.getLocation();

            detalis[6] = "2";


            Intent intent = new Intent(getApplicationContext(),EventInfo.class);
            intent.putExtra(Constants.EVENTINFORM,detalis);
            intent.putExtra(Constants.LOGGEDUSERID,user_id);
            int status_mode;
            if(receivedEventsMode == Constants.allEventsMode){
                status_mode = Constants.joinParentMode;
            }
            else{
                status_mode = Constants.nonParentMode;
            }
            intent.putExtra(Constants.parentActivityMode,status_mode);
            intent.putExtra(Constants.EVENT_ID,clickedData.getId());
            startActivity(intent);

        }
    }

    public class getUserEventsTask extends AsyncTask<Void, Void, String> {

        //        private Context context;
        private Context context;
        public getUserEventsTask(myEventsActivity activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        /** progress dialog to show user that the backup is processing. */
        private ProgressDialog dialog;
        /** application context. */
        private myEventsActivity activity;

        protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            try {

                Log.i(TAG, "");
                JSONObject cred = new JSONObject();
                try {
                    cred.put(NetworkUtilities.TOKEN, user_token);
                } catch (JSONException e) {
                    Log.i(TAG,e.toString());
                }
                try {
                    cred.put(NetworkUtilities.USER_ID, user_id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(receivedEventsMode == Constants.myEventsMode){

                    return NetworkUtilities.doPost(cred, NetworkUtilities.BASE_URL + "/get_events_by_user/");
                }
                else{
                    return NetworkUtilities.doPost(cred, NetworkUtilities.BASE_URL + "/get_all_events/");
                }

            } catch (Exception ex) {
                Log.e(TAG, "getUserEvents.doInBackground: failed to doPost");
                Log.i(TAG, ex.toString());
                return null;
            }
        }


        @Override
        protected void onPostExecute(final String responseString) {

            JSONObject myObject = null;
            String responseStatus = null;
            JSONArray message = null;
            try {
                myObject = new JSONObject(responseString);
                responseStatus = myObject.getString(Constants.RESPONSE_STATUS);
                message = myObject.getJSONArray(Constants.RESPONSE_MESSAGE);
            } catch (JSONException e)
            {
                Log.i(TAG, e.toString());
                e.printStackTrace();
            }

            if(myObject!=null && responseStatus!=null && message!=null){
                if(responseStatus.equals(Constants.RESPONSE_OK.toString())){
                    Log.i(TAG, "received user events: " + responseString);
                    final SortableCarTableView carTableView = (SortableCarTableView) findViewById(R.id.tableView);
                    assert carTableView != null;
                    try {
                        carTableView.setDataAdapter(new CarTableDataAdapter(context, createAndGiveCarList(message)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    carTableView.addDataClickListener(new CarClickListener());

                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
                else{
                    //User or Password doesn't match .. Forgot password?
                    Log.i(TAG, "something happen in getUserEvent");
                }

            }
            else{
                //Send HttpBadRequest to the server
            }


        }




    }
}
