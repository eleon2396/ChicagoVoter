package edu.uic.cs422.chicagovoter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class myItemAdapter extends BaseAdapter {

    private String[] statuses;
    private LayoutInflater mInflator;

    public myItemAdapter(String[] s){
        this.statuses = s;
    }

    @Override
    public int getCount() {
        return statuses.length;
    }

    @Override
    public Object getItem(int position) {
        return statuses[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.detailed_statuses, null);

        TextView status = (TextView)convertView.findViewById(R.id.statusTextView);
        status.setText(statuses[position]);


        return convertView;
    }
}
