package edu.uic.cs422.chicagovoter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class RepActivity extends AppCompatActivity {

    private ArrayList<Integer> facePicIDs = new ArrayList<Integer>(
            Arrays.asList(R.drawable.chuy, R.drawable.rkelly, R.drawable.edburke, R.drawable.robmaldonado,
                          R.drawable.rahm, R.drawable.brianhopkins, R.drawable.patdowell, R.drawable.sophiaking));
    private ArrayList<Representatives> repInfo = null;
    private String[] officeNames;
    private String[] names;
    private String[] parties;
    private String[] websites;
    private String[] emails;
    private String[] phoneNumbers;
    private String[] reElection;
    private Context mContext;
    private RepAdapter mAdapter;
    private ArrayList<Representatives> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rep);

        // create for when user uses the search bar
        results = new ArrayList<Representatives>();

        // create a new list of representatives
        repInfo = new ArrayList<Representatives>();

        // store application context
        mContext = getApplicationContext();

        // get information on each representatives
        officeNames = getResources().getStringArray(R.array.office_name);
        names = getResources().getStringArray(R.array.rep_names);
        parties = getResources().getStringArray(R.array.political_party);
        websites = getResources().getStringArray(R.array.website);
        emails = getResources().getStringArray(R.array.email);
        phoneNumbers = getResources().getStringArray(R.array.phone_numer);
        reElection = getResources().getStringArray(R.array.is_running_again);

        // store raw data into representative objects
        for(int i = 0; i < facePicIDs.size(); i++)
        {
            Representatives item = new Representatives(names[i], officeNames[i], facePicIDs.get(i), websites[i], emails[i], phoneNumbers[i], parties[i], reElection[i]);
            repInfo.add(item);
        }

        // create the list adapter
        ListView repList = (ListView) findViewById(R.id.rep_officials_listview);
        mAdapter = new RepAdapter(repInfo, this);
        repList.setAdapter(mAdapter);

        // create the toolbar
        Toolbar chicagoToolbar = (Toolbar) findViewById(R.id.rep_toolbar);
        setSupportActionBar(chicagoToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // set listener for keypresses on the edit text search bar
        initializeEditText();
    }

    public void initializeEditText()
    {
        // get the search bar and set the listener
        TextView searchBar = (TextView) findViewById(R.id.rep_search_edittext);

        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {

                // get the string
                TextView search = (TextView) findViewById(R.id.rep_search_edittext);
                String input = search.getText().toString();
                //Toast.makeText(mContext,input, Toast.LENGTH_LONG).show();

                // TODO: implement a search on the list of representatives based on the input


                // canned response: always just show rahm emanuel
                results.clear();
                results.add(repInfo.get(4));
                mAdapter.updateListview(results);
                mAdapter.notifyDataSetChanged();

                return true;
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

}
