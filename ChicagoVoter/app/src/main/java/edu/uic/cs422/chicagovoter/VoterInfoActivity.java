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
import java.util.regex.Pattern;

public class VoterInfoActivity extends AppCompatActivity {
    // * Broadcast receiver, filter, and string that will be used for changing the language in the app
    private static final String SETTINGS_INTENT="com.example.eleon.SETTINGS";
    private BroadcastReceiver mReceiver1;
    private IntentFilter mfilter1;

    // * Variable to hold all the polling stations
    private String[] pollingStations;
    private ListView mainList;

    TextView addressText;
    Button continueButton;

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

        Toolbar myTool = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myTool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        myTool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        if(check == null){
           // Toast.makeText(getApplicationContext(), "THIS IS EMPTY", Toast.LENGTH_LONG).show();
        }
        else{
            Intent getLan = getIntent();
            String language = getLan.getStringExtra("com.LAN");
            setAppLocale(language, getApplicationContext());
        }

        addressText = (TextView) findViewById(R.id.enterAddressEditText);
        continueButton = (Button)findViewById(R.id.nextButton);

        handleNextButton();
    }

    public void step1VoteInfo(String address){

        //Pattern p = Pattern.compile("\\d{1,5}\\s\\w.\\s(\\b\\w*\\b\\s){1,2}\\w*\\.");

        System.out.println(address);
        if(address.isEmpty()){
            Toast.makeText(getApplicationContext(), "THE ADDRESS IS EMPTY... TRY AGAIN", Toast.LENGTH_LONG).show();
        }
        else{
            Boolean check = Pattern.matches("\\d{1,5}\\s\\w.?\\s(\\b\\w*\\b\\s){1,2}\\w*\\.?", address);
            if(check){
                Toast.makeText(getApplicationContext(), "THE ADDRESS IS VALID", Toast.LENGTH_LONG).show();
                Intent step2Intent = new Intent(getApplicationContext(), VoterInfoActivity2.class);
                step2Intent.putExtra("com.Address", address);
                startActivity(step2Intent);
            }
            else{
                Toast.makeText(getApplicationContext(), "THE ADDRESS IS NOT VALID", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void handleNextButton(){
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step1VoteInfo(addressText.getText().toString());
            }
        });
    }



}
