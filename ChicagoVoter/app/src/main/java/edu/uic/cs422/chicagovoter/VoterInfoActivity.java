package edu.uic.cs422.chicagovoter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Random;

public class VoterInfoActivity extends AppCompatActivity {
    // * Broadcast receiver, filter, and string that will be used for changing the language in the app
    private static final String SETTINGS_INTENT="com.example.eleon.SETTINGS";
    private BroadcastReceiver mReceiver1;
    private IntentFilter mfilter1;

    // * Variable to hold all the polling stations
    private String[] pollingStations;
    private ListView mainList;

    private void receiver(){
        mfilter1 = new IntentFilter(SETTINGS_INTENT);
        mfilter1.setPriority(2);
        mReceiver1 = new myBroadcastReceiver();
        registerReceiver(mReceiver1, mfilter1);
    }
    private void setAppLocale(String localCode, Context context){
        Resources res  = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(localCode.toLowerCase()));
        }

       // res.updateConfiguration(conf, dm);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_info);

        receiver();
        pollingStations = getResources().getStringArray(R.array.pollingLocation);
        Intent intent = getIntent();
        String check = intent.getStringExtra("com.CHECK");

        Toolbar myTool = (Toolbar)findViewById(R.id.voter_toolbar);
        setSupportActionBar(myTool);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if(check == null){
           // Toast.makeText(getApplicationContext(), "THIS IS EMPTY", Toast.LENGTH_LONG).show();
        }
        else{
            Intent getLan = getIntent();
            String language = getLan.getStringExtra("com.LAN");
            setAppLocale(language, getApplicationContext());
        }

        handleSurvey();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.settings_action_items, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.back_button:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }


        return super.onOptionsItemSelected(item);
    }

    private void handleSurvey(){

        Button submitBTN = (Button)findViewById(R.id.SubmitButton);
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllInfoFromRadioGroups();
                handlePollingMap();
            }
        });
    }

    private void handlePollingMap(){
        final Button pollingButton = (Button)findViewById(R.id.goToMapsButton);
        final TextView address = (TextView)findViewById(R.id.addressEditText);
        final TextView pollingStation = (TextView) findViewById(R.id.pollingStationTextView);

        String s = address.getText().toString();
        String polling = getPollingLocation();
        updatePollingStation(s, polling);

        pollingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = address.getText().toString();
                String temp = pollingStation.getText().toString();
                String found = "";

                for(int i = 0; i < pollingStations.length; i++){
                    if(pollingStations[i] == temp){
                        found = temp;
                    }
                }

                updateMapsButton(s, found);
            }
        });
    }



    private String getPollingLocation(){
        Random r = new Random();
        int randomNum = r.nextInt(9 - 0) + 0;


        return pollingStations[randomNum];
    }

    private void updateMapsButton(String address, String polling){
        if(checkString(address)){
            Uri pollingAddress = Uri.parse("geo:37.7749,-122.4192?q=" + Uri.encode(polling));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, pollingAddress );
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);

        }
    }


    private void updatePollingStation(String address, String polling){

        if(checkString(address)) {

            final TextView pollingStation = (TextView) findViewById(R.id.pollingStationTextView);
            pollingStation.setText(polling);
        }
    }
    // * Assume correct input
    private boolean checkString(String address){

        if(address.isEmpty()){
            return false;
        }
        else{
            Log.i("CHECK2", address);
            return true;
        }
    }


    private void getAllInfoFromRadioGroups(){
        RadioGroup group1 = (RadioGroup)findViewById(R.id.statusRadioGroup);
        RadioGroup group2 = (RadioGroup)findViewById(R.id.q1RadioGroup);
        RadioGroup group3 = (RadioGroup)findViewById(R.id.q2RadioGroup);

        String status = handleStatusRadioGroup(group1);
        String question1Answer = handleStatusRadioGroup(group2);
        String question2Answer = handleStatusRadioGroup(group3);

        checkIfEligible(status, question1Answer, question2Answer);


    }

    private void checkIfEligible(String status, String q1, String q2){
        Resources res = getResources();
        TextView eligibleView = (TextView)findViewById(R.id.eligibleTextView);
        if(status == res.getString(R.string.Citizen) || status == res.getString(R.string.GreenCard)){
            if(q1 == res.getString(R.string.NoButton) && q2 == res.getString(R.string.YesButton)){
                eligibleView.setText("You are Eligible to VOTE!!");
            }
            else{
                eligibleView.setText("You are NOT Eligible to VOTE!!");
            }
        }
        else{
            eligibleView.setText("You are NOT Eligible to VOTE!!");
        }
    }

    private String handleStatusRadioGroup(RadioGroup group){

        int radioButtonID = group.getCheckedRadioButtonId();

        View radioButton = group.findViewById(radioButtonID);
        int idx = group.indexOfChild(radioButton);

        RadioButton r = (RadioButton) group.getChildAt(idx);
        String selectedtext = r.getText().toString();
        return selectedtext;
    }




}
