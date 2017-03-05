package com.example.android.sendmoods;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Etiennera on 2017-03-04.
 */

public class MoodListAdapter extends BaseAdapter {
    private static ArrayList<Mood> moodList;

    private LayoutInflater mInflater;

    public MoodListAdapter(Context context, ArrayList<Mood> list) {
        moodList = list;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return moodList.size();
    }

    public Object getItem(int position) {
        return moodList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_entry, null);
            holder = new ViewHolder();
            holder.nameEntry = (TextView) convertView.findViewById(R.id.entry_name);
            holder.chestEntry = (TextView) convertView.findViewById(R.id.entry_chest);
            holder.bustEntry = (TextView)convertView.findViewById(R.id.entry_bust);
            holder.waistEntry = (TextView) convertView.findViewById(R.id.entry_waist);
            holder.inseamEntry = (TextView) convertView.findViewById(R.id.entry_inseam);
            convertView.setTag(holder); //set the tag
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.d("ADAPTERLOOP", moodList.get(position).toString());
        holder.nameEntry.setText(moodList.get(position).getUsername());
        holder.chestEntry.setText(String.format("Chest: %.1fin", moodList.get(position).getDateTime()));
        holder.bustEntry.setText(String.format("Bust: %.1fin", moodList.get(position).getAddress()));
        holder.waistEntry.setText(String.format("Waist: %.1fin", moodList.get(position).getReason()));
        holder.inseamEntry.setText(String.format("Inseam: %.1fin", moodList.get(position).getEmotion()));
        return convertView;
    }

    static class ViewHolder {
        TextView nameEntry;
        TextView chestEntry;
        TextView bustEntry;
        TextView waistEntry;
        TextView inseamEntry;
    }
}