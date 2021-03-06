package com.example.mostafawattad.sportbuddy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.app.ProgressDialog;
import android.widget.Toast;



import com.example.mostafawattad.sportbuddy.client.NetworkUtilities;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private View mProgressView;
    private View mLoginFormView;

    private String usrid;
    private getUserEventsTask myEventsTask = null;
    private static final String TAG = " MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    /* change buttons font text - lina */

        Intent prev  = getIntent();
        usrid = prev.getStringExtra(Constants.LOGGEDUSERID);

        Typeface myTypeFace = Typeface.createFromAsset(getAssets(),"fontBtn.ttf");
        Button joinBtn = (Button)findViewById(R.id.join_btn);
        joinBtn.setTypeface(myTypeFace);
        Button createBtn = (Button)findViewById(R.id.create_btn);
        createBtn.setTypeface(myTypeFace);
        Button myGroupsBtn = (Button)findViewById(R.id.myGroups_btn);
        myGroupsBtn.setTypeface(myTypeFace);
        mLoginFormView = findViewById(R.id.login_form);
//        mProgressView = findViewById(R.id.login_progress_1);
    }

    @Override
    public void onBackPressed() {
    }
    public void joinClick(View v)
    {
        myEventsTask = new getUserEventsTask(this,Constants.allEventsMode);
        v.setBackgroundColor(0xAD1F2D);
        myEventsTask.execute((Void) null);
    }

    public void createClick(View v)
    {

        Intent intent = new Intent(getApplicationContext(), CreateEventSc.class);
        intent.putExtra(Constants.LOGGEDUSERID,usrid);
        intent.putExtra(Constants.parentActivityMode,Constants.createParentMode);
        v.setBackgroundColor(0xAD1F2D);
        startActivity(intent);
    }

    public void myGroupsClick(View v)
    {
//        Uri uri = Uri.parse("http://www.walla.com");
//        showProgress(true);
        myEventsTask = new getUserEventsTask(this,Constants.myEventsMode);
        v.setBackgroundColor(0xAD1F2D);
        myEventsTask.execute((Void) null);


    }
    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class getUserEventsTask extends AsyncTask<Void, Void, String> {

//        private Context context;
        private Context context;
        public getUserEventsTask(MainActivity activity,int eventsMode) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
            this.eventsMode = eventsMode;
        }

        /** progress dialog to show user that the backup is processing. */
        private ProgressDialog dialog;
        /** application context. */
        private MainActivity activity;
        private int eventsMode;

        protected void onPreExecute() {
            this.dialog.setMessage("Getting your events..");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            Intent nextIntent = null;
            Intent prev  = getIntent();
            String usrid = prev.getStringExtra(Constants.LOGGEDUSERID);
            if(this.eventsMode == Constants.myEventsMode){

                nextIntent = new Intent(getApplicationContext(),myEventsActivity.class);
                nextIntent.putExtra(Constants.eventsMode,Constants.myEventsMode);
            }
            else{
                nextIntent = new Intent(getApplicationContext(),myEventsActivity.class);
                nextIntent.putExtra(Constants.eventsMode,Constants.allEventsMode);
            }

            nextIntent.putExtra(Constants.LOGGEDUSERID, usrid);
            startActivity(nextIntent);
            return null;
        }


        @Override
        protected void onPostExecute(final String responseString) {
//            onAuthenticationResult(authToken);
            dialog.cancel();

        }




    }
}
