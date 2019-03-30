package edu.uic.cs422.chicagovoter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EventsAdapter extends BaseAdapter
{
    private List<Events> eventList;
    private Context pContext;

    public EventsAdapter(List<Events> eventList, Context mContext)
    {
        this.eventList = eventList;
        this.pContext = mContext;
    }

    @Override
    public int getCount()
    {
        return eventList.size();
    }

    @Override
    public long getItemId(int pos)
    {
        return pos;
    }

    @Override
    public Events getItem(int i)
    {
        return eventList.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        // reuse and recycle
        if(convertView == null)
            convertView = LayoutInflater.from(pContext).inflate(R.layout.events_layout, parent, false);


        TextView eventName = (TextView) convertView.findViewById(R.id.events_text_view);
        TextView date = (TextView) convertView.findViewById(R.id.date_text_view);
        TextView loc = (TextView) convertView.findViewById(R.id.location_text_view);
        TextView desc = (TextView) convertView.findViewById(R.id.event_desc_text_view);

        // add items to the listview
        Events item = eventList.get(position);
        eventName.setText(item.eventName);
        date.setText(item.eventDate);
        loc.setText(item.eventLocation);
        desc.setText(item.eventDesc);

        return convertView;
    }
}
