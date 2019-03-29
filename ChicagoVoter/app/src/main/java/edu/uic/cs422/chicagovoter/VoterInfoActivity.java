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
import android.util.DisplayMetrics;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Locale;

public class VoterInfoActivity extends AppCompatActivity {
    private static final String SETTINGS_INTENT="com.example.eleon.SETTINGS";
    //Broadcast receiver and filter that will be used
    private BroadcastReceiver mReceiver1;
    private IntentFilter mfilter1;

    private String[] myStatuses;
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

        res.updateConfiguration(conf, dm);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_info);

        receiver();

        Intent intent = getIntent();
        String check = intent.getStringExtra("com.CHECK");
        if(check == null){
            Toast.makeText(getApplicationContext(), "THIS IS EMPTY", Toast.LENGTH_LONG).show();
        }
        else{
            Intent getLan = getIntent();
            String language = getLan.getStringExtra("com.LAN");
            setAppLocale(language, getApplicationContext());
        }





    }
}
