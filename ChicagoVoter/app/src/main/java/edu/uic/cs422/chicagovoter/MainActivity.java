package edu.uic.cs422.chicagovoter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.action_items, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar chicagoToolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(chicagoToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


}
