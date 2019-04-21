package edu.uic.cs422.chicagovoter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String SETTINGS_INTENT="com.example.eleon.SETTINGS";

    //Broadcast receiver and filter that will be used
    private BroadcastReceiver mReceiver1;
    private IntentFilter mfilter1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.action_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.setting_button:
                goToSettings();
                break;
                default:
                    return super.onOptionsItemSelected(item);
        }


        return super.onOptionsItemSelected(item);
    }

    public void goToSettings()
    {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }


    private void receiver(){
        mfilter1 = new IntentFilter(SETTINGS_INTENT);
        mfilter1.setPriority(1);
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

        setContentView(R.layout.activity_main);

        Toolbar chicagoToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(chicagoToolbar);


        receiver();

        Intent intent = getIntent();
        String check = intent.getStringExtra("com.CHECK");
        if(check == null){

        }
        else{
            Intent getLan = getIntent();
            String language = getLan.getStringExtra("com.LAN");
            setAppLocale(language, getApplicationContext());
        }

        startVoterActivity();
        initializeRepButtonActivity();
        initializeCurrentEventsActivity();
    }

    private void initializeRepButtonActivity()
    {
        Button repButton = (Button) findViewById(R.id.my_representatives_button);
        repButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), RepActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startVoterActivity(){
        Button voterBtn = (Button)findViewById(R.id.voter_information_button);
        voterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VoterInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeCurrentEventsActivity()
    {
        Button currButton = (Button) findViewById(R.id.current_events_button);
        currButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), CurrentEventsActivity.class);
                startActivity(intent);
            }
        });
    }


}
