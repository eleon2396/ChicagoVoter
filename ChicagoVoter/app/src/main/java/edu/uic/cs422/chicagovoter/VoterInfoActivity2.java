package edu.uic.cs422.chicagovoter;

import android.content.Intent;
import android.os.health.SystemHealthManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class VoterInfoActivity2 extends AppCompatActivity {

    private Button nextButton;
    private RadioGroup citizenship;
    private RadioGroup felony;
    private RadioGroup livedInChicago;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_info2);


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
        final Intent getIntent = getIntent();
        final String address = getIntent.getStringExtra("com.Address");

        nextButton = (Button)findViewById(R.id.nextButton2);
        citizenship = (RadioGroup)findViewById(R.id.citizenshipRadioGroup);
        felony = (RadioGroup)findViewById(R.id.felonyRadioGroup);
        livedInChicago = (RadioGroup)findViewById(R.id.livedInChicagoRadioGroup);



        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String statusChoice = handleRadioGroups(citizenship);
                String felonyChoice = handleRadioGroups(felony);
                String livedInChicagoChoice = handleRadioGroups(livedInChicago);
                System.out.println(statusChoice + " " + felonyChoice + " " + livedInChicagoChoice );

                if(statusChoice.matches(" ") || felonyChoice.matches(" ") || livedInChicagoChoice.matches(" ")){
                    Toast.makeText(getApplicationContext(), "PLEASE SELECT AN OPTION IN ALL FIELDS BEFORE CLICKING 'CONTINUE'...", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent lastStep = new Intent(getApplicationContext(), VoterInfoActivity3.class);
                    lastStep.putExtra("com.Address", address);
                    lastStep.putExtra("com.Status", statusChoice);
                    lastStep.putExtra("com.Felony", felonyChoice);
                    lastStep.putExtra("com.Lived", livedInChicagoChoice);
                    startActivity(lastStep);
                }


            }
        });

    }


    private String handleRadioGroups(RadioGroup group){

        if(group.getCheckedRadioButtonId() != -1) {
            int radioButtonID = group.getCheckedRadioButtonId();
            View radioButton = group.findViewById(radioButtonID);
            int index = group.indexOfChild(radioButton);

            RadioButton r = (RadioButton) group.getChildAt(index);
            String selectedText = r.getText().toString();
            return selectedText;
        }
        return " ";
    }


}
