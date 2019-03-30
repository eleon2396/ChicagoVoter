package edu.uic.cs422.chicagovoter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CandidateAdapter extends BaseAdapter
{
    private List<Candidates> candidatesList;
    private Context pContext;

    public CandidateAdapter(List<Candidates> candidatesList, Context pContext)
    {
        this.candidatesList = candidatesList;
        this.pContext = pContext;
    }

    @Override
    public int getCount()
    {
        return candidatesList.size();
    }

    @Override
    public long getItemId(int pos)
    {
        return pos;
    }

    @Override
    public Candidates getItem(int i)
    {
        return candidatesList.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        // reuse and recycle
        if(convertView == null)
            convertView = LayoutInflater.from(pContext).inflate(R.layout.candidates_layout, parent, false);

        ImageView candidateFace = (ImageView) convertView.findViewById(R.id.candidate_image);
        TextView candidateName = (TextView) convertView.findViewById(R.id.candidate_name_text_view);
        TextView candidateRunningFor = (TextView) convertView.findViewById(R.id.candidate_running_for_text_view);
        TextView candidateParty = (TextView) convertView.findViewById(R.id.candidate_party_pos);
        TextView candidateContact = (TextView) convertView.findViewById(R.id.candidate_contact_info);
        TextView candidateWebsite = (TextView) convertView.findViewById(R.id.candidate_website);

        // add items to the listview
        Candidates item = candidatesList.get(position);
        candidateFace.setImageResource(item.imgId);
        candidateName.setText(item.name);
        candidateParty.setText(item.partyAffiliation);
        candidateContact.setText(item.contact);
        candidateWebsite.setText(item.website);
        candidateRunningFor.setText(item.officeRunningFor);

        return convertView;
    }
}
