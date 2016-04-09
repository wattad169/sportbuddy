package com.example.mostafawattad.sportbuddy;

/**
 * Created by mostafawattad on 07/04/2016.
 */


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevents);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        myEventsTask = new getUserEventsTask(this);
        myEventsTask.execute((Void) null);


    }



    private static List<event> createAndGiveCarList(JSONArray inputJson) throws JSONException {
        final List<event> events = new ArrayList<>();
        for (int i=0; i < inputJson.length() ; i++){
            JSONObject currentEvent = (JSONObject) inputJson.get(i);
            String event_type = currentEvent.getString(Constants.EVENT_TYPE);
            String event_name = currentEvent.getString(Constants.EVENT_NAME);
            String event_date = currentEvent.getString(Constants.EVENT_DATE);
            String event_location = currentEvent.getString(Constants.EVENT_LOCATION);
            int event_size = currentEvent.getJSONArray(Constants.EVENT_MEMBERS).length();

            Log.i(TAG, event_type);
            final EventType type = new EventType(Constants.sportTypeToLogo.get(event_type), event_type);
            final event current_event = new event(type,event_name ,event_date,event_location,event_size, 150, 25000);
            events.add(current_event);
            }

//        final EventType audi = new EventType(R.mipmap.swimming, "football");
//        final event audiA1 = new event(audi, "A1", 150, 25000);
//        final event audiA3 = new event(audi, "A3", 120, 35000);
//        final event audiA4 = new event(audi, "A4", 210, 42000);
//        final event audiA5 = new event(audi, "S5", 333, 60000);
//        final event audiA6 = new event(audi, "A6", 250, 55000);
//        final event audiA7 = new event(audi, "A7", 420, 87000);
//        final event audiA8 = new event(audi, "A8", 320, 110000);
//
//        final EventType bmw = new EventType(R.mipmap.cycling, "soccer");
//        final event bmw1 = new event(bmw, "1er", 170, 25000);
//        final event bmw3 = new event(bmw, "3er", 230, 42000);
//        final event bmwX3 = new event(bmw, "X3", 230, 45000);
//        final event bmw4 = new event(bmw, "4er", 250, 39000);
//        final event bmwM4 = new event(bmw, "M4", 350, 60000);
//        final event bmw5 = new event(bmw, "5er", 230, 46000);
//
//        final EventType porsche = new EventType(R.mipmap.football, "swimming");
//        final event porsche911 = new event(porsche, "911", 280, 45000);
//        final event porscheCayman = new event(porsche, "Cayman", 330, 52000);
//        final event porscheCaymanGT4 = new event(porsche, "Cayman GT4", 385, 86000);
//
//        final List<event> cars = new ArrayList<>();
//        cars.add(audiA3);
//        cars.add(audiA1);
//        cars.add(porscheCayman);
//        cars.add(audiA7);
//        cars.add(audiA8);
//        cars.add(audiA4);
//        cars.add(bmwX3);
//        cars.add(porsche911);
//        cars.add(bmw1);
//        cars.add(audiA6);
//        cars.add(audiA5);
//        cars.add(bmwM4);
//        cars.add(bmw5);
//        cars.add(porscheCaymanGT4);
//        cars.add(bmw3);
//        cars.add(bmw4);

        return events;
    }


    private class CarClickListener implements TableDataClickListener<event> {

        @Override
        public void onDataClicked(final int rowIndex, final event clickedData) {
            final String carString = clickedData.getType().getName() + " " + clickedData.getName();
            Toast.makeText(myEventsActivity.this, carString, Toast.LENGTH_SHORT).show();
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
                // Simulate network access.
                String user_token = "5642554087309312";
                String user_id    = user_token;
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
                return NetworkUtilities.doPost(cred, NetworkUtilities.BASE_URL + "/get_all_events/");
            } catch (Exception ex) {
                Log.e(TAG, "getUserEvents.doInBackground: failed to doPost");
                Log.i(TAG, ex.toString());
                return null;
            }
        }


        @Override
        protected void onPostExecute(final String responseString) {
//            onAuthenticationResult(authToken);


//
//            MessageListAdapter adapter = new MessageListAdapter(activity, titles);
//            setListAdapter(adapter);
//            adapter.notifyDataSetChanged();




            JSONObject myObject = null;
            String responseStatus = null;
            JSONArray message = null;
            try {
                myObject = new JSONObject(responseString);
                responseStatus = myObject.getString(Constants.RESPONSE_STATUS);
                message = myObject.getJSONArray(Constants.RESPONSE_MESSAGE);
            } catch (JSONException e) {
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
                    Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();
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
