package edu.uic.cs422.chicagovoter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class RepAdapter extends BaseAdapter {

    private List<Representatives> repInfo;
    private Context pContext;

    private TextView office;
    private TextView name;
    private TextView party;
    private TextView website;
    private TextView email;
    private TextView phoneNumber;
    private TextView reElection;
    private ImageView repFace;

    public RepAdapter(List<Representatives> repInfo, Context pContext)
    {
        this.repInfo = repInfo;
        this.pContext = pContext;
    }

    public void updateListview(List<Representatives> newList)
    {
        repInfo = newList;
    }

    @Override
    public int getCount()
    {
        return repInfo.size();
    }

    @Override
    public long getItemId(int pos)
    {
        return pos;
    }

    @Override
    public Representatives getItem(int i)
    {
        return repInfo.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        // reuse and recycle
        if(convertView == null)
            convertView = LayoutInflater.from(pContext).inflate(R.layout.rep_layout, parent, false);


        repFace = (ImageView) convertView.findViewById(R.id.rep_image);
        reElection = (TextView) convertView.findViewById(R.id.reelection_rep);
        phoneNumber = (TextView) convertView.findViewById(R.id.phone_contact_rep);
        email = (TextView) convertView.findViewById(R.id.email_rep);
        website = (TextView) convertView.findViewById(R.id.website_rep);
        party = (TextView) convertView.findViewById(R.id.political_party_rep);
        name = (TextView) convertView.findViewById(R.id.official_name_rep);
        office = (TextView) convertView.findViewById(R.id.office_name_rep);

        // add items to the listview
        Representatives item = repInfo.get(position);
        repFace.setImageResource(item.imgID);
        reElection.setText(item.reElection);
        phoneNumber.setText(item.phoneNumber);
        email.setText(item.email);
        website.setText(item.website);
        party.setText(item.party);
        name.setText(item.repName);
        office.setText(item.office);

        return convertView;
    }
}
