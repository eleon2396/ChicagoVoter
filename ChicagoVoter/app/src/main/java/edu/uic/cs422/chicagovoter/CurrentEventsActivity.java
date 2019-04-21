package edu.uic.cs422.chicagovoter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurrentEventsActivity extends AppCompatActivity {

    private ArrayList<Integer> candidateImages = new ArrayList<Integer>(Arrays.asList(
            R.drawable.alexander_acavedo, R.drawable.byron_lopez, R.drawable.melissa_ervin, R.drawable.ameya_pawar,
            R.drawable.toni_pre, R.drawable.lori_lightfoot));
    private String[] candidateNames;
    private String[] candidateRunningPositions;
    private String[] candidatePartyAffil;
    private String[] candidateContactInfo;
    private String[] candidateWebsites;
    private ArrayList<Candidates> candidatesList = null;
    private CandidateAdapter candidateAdapter;

    private String[] eventNames;
    private String[] dates;
    private String[] locations;
    private String[] descriptions;
    private ArrayList<Events> eventsLists = null;
    private EventsAdapter eventAdapter;

    private String[] candidateClickableSites;
    private String[] eventsClickableSites;
    private boolean buttonSwitch = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_events);

        Toolbar chicagoToolbar = (Toolbar) findViewById(R.id.curr_toolbar);
        setSupportActionBar(chicagoToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        chicagoToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        candidateClickableSites =  getResources().getStringArray(R.array.candidate_click_web);
        eventsClickableSites = getResources().getStringArray(R.array.event_websites);

        // get strings for events going on in chicago
        eventNames = getResources().getStringArray(R.array.event_name);
        dates = getResources().getStringArray(R.array.event_date);
        locations = getResources().getStringArray(R.array.event_location);
        descriptions = getResources().getStringArray(R.array.description_events);

        // populate raw data into an events object list
        eventsLists = new ArrayList<Events>();
        for(int i = 0; i < eventNames.length; i++)
        {
            Events item = new Events(eventNames[i], dates[i], locations[i], descriptions[i]);
            eventsLists.add(item);
        }

        //get strings for candidates in chicago
        candidateNames = getResources().getStringArray(R.array.candidate_names);
        candidateRunningPositions = getResources().getStringArray(R.array.running_for_office);
        candidatePartyAffil = getResources().getStringArray(R.array.candidate_political_party);
        candidateContactInfo = getResources().getStringArray(R.array.candidate_contact_info);
        candidateWebsites = getResources().getStringArray(R.array.candidate_website);

        //populate raw data into candidate object list
        candidatesList = new ArrayList<Candidates>();
        for(int i = 0; i < candidateNames.length; i++)
        {
            Candidates item = new Candidates(candidateImages.get(i), candidateNames[i], candidateRunningPositions[i], candidatePartyAffil[i], candidateContactInfo[i], candidateWebsites[i]);
            candidatesList.add(item);
        }

        // create custom adapters
        eventAdapter = new EventsAdapter(eventsLists, this);
        candidateAdapter = new CandidateAdapter(candidatesList, this);

        ListView currEvents = (ListView) findViewById(R.id.current_events_listview);
        currEvents.setAdapter(candidateAdapter);

        setEventsListener();
        setCandidatesListener();
        currEvents.setOnItemClickListener(listListener);
    }

    private ListView.OnItemClickListener listListener = new ListView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id)
        {
            if(buttonSwitch)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(eventsClickableSites[position]));
                startActivity(intent);
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(candidateClickableSites[position]));
                startActivity(intent);
            }
        }
    };

    public void setEventsListener()
    {
        Button eventBtn = (Button) findViewById(R.id.events_button);
        eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView currEvents = (ListView) findViewById(R.id.current_events_listview);
                currEvents.setAdapter(eventAdapter);
                buttonSwitch = true;
                Button eventBtn = (Button) findViewById(R.id.events_button);
                Button candidateBtn = (Button) findViewById(R.id.candidates_button);

                eventBtn.setBackgroundColor(Color.parseColor("#c0c9d6"));
                candidateBtn.setBackgroundColor(Color.parseColor("#e3e9f2"));
                //eventBtn.getBackground().setColorFilter(Color.parseColor("#e3e9f2"), PorterDuff.Mode.DARKEN);
                //candidateBtn.getBackground().setColorFilter(Color.parseColor("#c0c9d6"), PorterDuff.Mode.DARKEN);
            }
        });
    }

    public void setCandidatesListener()
    {
        Button candidateBtn = (Button) findViewById(R.id.candidates_button);
        candidateBtn.setBackgroundColor(Color.parseColor("#c0c9d6"));

        candidateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView currEvents = (ListView) findViewById(R.id.current_events_listview);
                currEvents.setAdapter(candidateAdapter);
                buttonSwitch = false;
                Button eventBtn = (Button) findViewById(R.id.events_button);
                Button candidateBtn = (Button) findViewById(R.id.candidates_button);

                eventBtn.setBackgroundColor(Color.parseColor("#e3e9f2"));
                candidateBtn.setBackgroundColor(Color.parseColor("#c0c9d6"));
            }
        });
    }

}
