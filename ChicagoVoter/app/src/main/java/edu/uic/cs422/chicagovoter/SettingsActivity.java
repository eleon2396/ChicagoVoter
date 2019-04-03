package edu.uic.cs422.chicagovoter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    private static final String SETTINGS_INTENT="com.example.eleon.SETTINGS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // * Set up the toolbar with back button
        Toolbar chicagoToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(chicagoToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // * Set up the change language button for spanish
        Button changeToSpanishBTN = (Button)findViewById(R.id.changeToSpanishBTN);
        Button changeToEngBTN = (Button)findViewById(R.id.changeToEnglishBTN);
        changeToSpanishBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // * Change the app locale to spanish and broadcast to other activities, then finish
                setAppLocale("sp");
                sendLan("sp");
                finish();

            }
        });

        // * Set up the change language button for english
        changeToEngBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // * Change the app locale to english and broadcast to other activities, the finish
                setAppLocale("en");
                sendLan("en");
                finish();
            }
        });


        // * Switch for Notifications but we havent set up yet what kind of notifactions, for now a Toast
        Switch notificationSwitch  = (Switch)findViewById(R.id.notificationSwitch);
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(getApplicationContext(), "THE NOTIFICATIONS ARE ON", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "THE NOTIFICATIONS ARE OFF", Toast.LENGTH_LONG).show();
                }
            }
        });


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
        // * Trigger events with the options item selected
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

    // * This function will send out an ordered broadcast to the other activities so that they can change the language of the app
    private void sendLan(String language){
        Intent changeLan = new Intent(SETTINGS_INTENT);
        changeLan.putExtra("com.LAN",  language);
        sendOrderedBroadcast(changeLan, null);
    }


    private void RestartActivity(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    // * Function to change the app locale based on the string that is passed
    private void setAppLocale(String localCode){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(localCode.toLowerCase()));
        }
        res.updateConfiguration(conf, dm);

    }

}
