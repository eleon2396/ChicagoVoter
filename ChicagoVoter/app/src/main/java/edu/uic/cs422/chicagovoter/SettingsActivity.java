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

        Toolbar chicagoToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        setSupportActionBar(chicagoToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Button changeToSpanishBTN = (Button)findViewById(R.id.changeToSpanishBTN);
        Button changeToEngBTN = (Button)findViewById(R.id.changeToEnglishBTN);
        changeToSpanishBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setAppLocale("sp");
                sendLan("sp");
                finish();

            }
        });

        changeToEngBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppLocale("en");
                sendLan("en");
                finish();
            }
        });


        //Switch for Notifications but we havent set up yet what kind of notifactions, for now a Toast
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

    private void sendLan(String language){
        Intent changeLan = new Intent(SETTINGS_INTENT);
        changeLan.putExtra("com.LAN",  language);
        sendOrderedBroadcast(changeLan, null);
    }

//    @Override
//    public void onBackPressed(){
//        finish();
//    }


    private void RestartActivity(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


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
