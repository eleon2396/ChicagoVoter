package edu.uic.cs422.chicagovoter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class myBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String getLan = intent.getStringExtra("com.LAN");
        Intent start = new Intent(context, MainActivity.class);
        start.putExtra("com.LAN", getLan);
        context.startActivity(start);



    }
}
