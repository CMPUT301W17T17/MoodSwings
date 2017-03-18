package com.example.android.sendmoods;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MoodListAdapter extends BaseAdapter {
    private static ArrayList<MoodEvent> moodEventList;

    private LayoutInflater mInflater;
    private Context context;

    public MoodListAdapter(Context context, ArrayList<MoodEvent> list) {
        moodEventList = list;
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getCount() {
        return moodEventList.size();
    }

    public Object getItem(int position) {
        return moodEventList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_history, null);
            holder = new ViewHolder();
            holder.usernameEntry = (TextView) convertView.findViewById(R.id.user_name);
            holder.moodWordEntry = (TextView) convertView.findViewById(R.id.mood_word);
            holder.dateEntry = (TextView) convertView.findViewById(R.id.mood_date_time);
            holder.colorBox = (RelativeLayout) convertView.findViewById(R.id.list_item_box);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.d("ADAPTERLOOP", moodEventList.get(position).toString());
        holder.usernameEntry.setText(moodEventList.get(position).getUsername());
        holder.moodWordEntry.setText(moodEventList.get(position).getMood().getText());
        holder.dateEntry.setText(moodEventList.get(position).getDate());
        holder.colorBox.setBackground(ContextCompat.getDrawable(context, moodEventList.get(position).getMood().getShape()));
        return convertView;
    }

    static class ViewHolder {
        TextView usernameEntry;
        TextView moodWordEntry;
        TextView dateEntry;
        RelativeLayout colorBox;
    }
}