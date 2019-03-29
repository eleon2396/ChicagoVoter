package edu.uic.cs422.chicagovoter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class RepActivity extends AppCompatActivity {

    private ArrayList<Integer> facePicIDs = new ArrayList<Integer>(
            Arrays.asList(R.drawable.chuy, R.drawable.rkelly, R.drawable.edburke, R.drawable.robmaldonado,
                          R.drawable.rahm, R.drawable.brianhopkins, R.drawable.patdowell, R.drawable.sophiaking));
    private ArrayList<Representatives> repInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rep);

        repInfo = new ArrayList<Representatives>();

        String[] officeNames = getResources().getStringArray(R.array.office_name);
        String[] names = getResources().getStringArray(R.array.rep_names);
        String[] parties = getResources().getStringArray(R.array.political_party);
        String[] websites = getResources().getStringArray(R.array.website);
        String[] emails = getResources().getStringArray(R.array.email);
        String[] phoneNumbers = getResources().getStringArray(R.array.phone_numer);
        String[] reElection = getResources().getStringArray(R.array.is_running_again);

        for(int i = 0; i < facePicIDs.size(); i++)
        {
            Representatives item = new Representatives(names[i], officeNames[i], facePicIDs.get(i), websites[i], emails[i], phoneNumbers[i], parties[i], reElection[i]);
            repInfo.add(item);
        }

        ListView repList = (ListView) findViewById(R.id.rep_officials_listview);
        repList.setAdapter(new RepAdapter(repInfo, this));

    }
}
