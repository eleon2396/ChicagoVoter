package edu.uic.cs422.chicagovoter;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class VoterInfoActivity3 extends AppCompatActivity {

    private TextView eligibleToVote;
    private TextView pollingLocation;
    private Button homePage;
    private String[] pollingStations;
    private Button maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_info3);

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
        Intent intent = getIntent();
        String address = intent.getStringExtra("com.Address");
        String status = intent.getStringExtra("com.Status");
        String felony = intent.getStringExtra("com.Felony");
        String lived = intent.getStringExtra("com.Lived");

        pollingStations = getResources().getStringArray(R.array.pollingLocation);


        eligibleToVote = (TextView)findViewById(R.id.ableToVoteAnswerTextView);
        pollingLocation = (TextView)findViewById(R.id.pollingTextView);




        System.out.println(status + " " + felony + " " + lived );
        checkIfEligible(status, felony, lived);

        homePage = (Button)findViewById(R.id.endButton);
        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBackHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBackHome);
            }
        });


    }

    private void handleMaps(final String polling){
        maps = (Button)findViewById(R.id.mapsButton);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMaps = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.7749,-122.4192?q=" + Uri.encode(polling)));
                openMaps.setPackage("com.google.android.apps.maps");
                startActivity(openMaps);
            }
        });
    }

    private void checkIfEligible(String status, String q1, String q2){
        Resources res = getResources();
         String getPollingPlace = getPollingLocation();
        if(status.matches(res.getString(R.string.Citizen)) || status.matches(res.getString(R.string.GreenCard))){
            if(q1.matches(res.getString(R.string.NoButton)) && q2.matches(res.getString(R.string.YesButton))){
                eligibleToVote.setText(res.getString(R.string.CanVote));
                eligibleToVote.setTextColor(Color.parseColor("#2196F3"));
                pollingLocation.setText(getPollingPlace);
                handleMaps(getPollingPlace);

            }
            else{
                eligibleToVote.setText(res.getString(R.string.CantVote));
                eligibleToVote.setTextColor(Color.parseColor("#D60B4F"));
                pollingLocation.setText(getPollingPlace);
                handleMaps(getPollingPlace);
            }
        }
        else{
            eligibleToVote.setText(res.getString(R.string.CantVote));
            eligibleToVote.setTextColor(Color.parseColor("#D60B4F"));
            pollingLocation.setText(getPollingPlace);
            handleMaps(getPollingPlace);
        }
    }

    private String getPollingLocation(){
        Random r = new Random();
        int randomNum = r.nextInt(9 - 0) + 0;
        return pollingStations[randomNum];
    }

}
