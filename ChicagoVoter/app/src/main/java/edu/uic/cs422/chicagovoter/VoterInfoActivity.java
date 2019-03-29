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
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

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
           // Toast.makeText(getApplicationContext(), "THIS IS EMPTY", Toast.LENGTH_LONG).show();
        }
        else{
            Intent getLan = getIntent();
            String language = getLan.getStringExtra("com.LAN");
            setAppLocale(language, getApplicationContext());
        }

        handleSurvey();
    }

    private void handleSurvey(){

        EditText nameText = (EditText)findViewById(R.id.nameEditText);
        EditText addressText = (EditText)findViewById(R.id.addressEditText);



        //All four options

/*        Button q1Yes = (Button)findViewById(R.id.q1YesButton);
        Button q1No = (Button)findViewById(R.id.q1NoButton);
        Button q2Yes = (Button)findViewById(R.id.q2YesButton);
        Button q2No = (Button)findViewById(R.id.q2NoButton);*/



        Button submitBTN = (Button)findViewById(R.id.SubmitButton);
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllInfoFromRadioGroups();
            }
        });
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
                eligibleView.setText("NAH NIBBA!!");
            }
        }
        else{
            eligibleView.setText("NAH NIBBA!!");
        }
    }

    private String handleStatusRadioGroup(RadioGroup group){

        int radioButtonID = group.getCheckedRadioButtonId();

        View radioButton = group.findViewById(radioButtonID);
        int idx = group.indexOfChild(radioButton);

        RadioButton r = (RadioButton) group.getChildAt(idx);
        String selectedtext = r.getText().toString();

        Log.i("TEST1", selectedtext);
        return selectedtext;
    }


}
