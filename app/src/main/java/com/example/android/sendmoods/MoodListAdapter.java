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
<<<<<<< HEAD
            holder.dateTimeEntry = (TextView) convertView.findViewById(R.id.mood_date_time);
=======
            holder.dateEntry = (TextView) convertView.findViewById(R.id.mood_date_time);
>>>>>>> jamaledd
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.d("ADAPTERLOOP", moodEventList.get(position).toString());
        holder.usernameEntry.setText(moodEventList.get(position).getUsername());
        holder.moodWordEntry.setText(moodEventList.get(position).getEmotion());
<<<<<<< HEAD
        holder.dateTimeEntry.setText(moodEventList.get(position).getDateTime().toString());
=======
        holder.dateEntry.setText(moodEventList.get(position).getDate());
>>>>>>> jamaledd
        convertView.setBackground(moodEventList.get(position).getListBox(context));
        return convertView;
    }

    static class ViewHolder {
        TextView usernameEntry;
        TextView moodWordEntry;
<<<<<<< HEAD
        TextView dateTimeEntry;
=======
        TextView dateEntry;
>>>>>>> jamaledd
    }
}