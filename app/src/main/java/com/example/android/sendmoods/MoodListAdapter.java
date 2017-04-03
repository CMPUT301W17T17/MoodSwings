package com.example.android.sendmoods;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     *
     * Gets the list view and attaches the values for the view of each value of that adapter.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_history, null);
            holder = new ViewHolder();
            holder.usernameEntry = (TextView) convertView.findViewById(R.id.user_name);
            holder.moodWordEntry = (TextView) convertView.findViewById(R.id.mood_word);
            holder.dateEntry = (TextView) convertView.findViewById(R.id.mood_date_time);
            holder.moodIcon = (ImageView) convertView.findViewById(R.id.list_box_icon);
            holder.colorBox = (RelativeLayout) convertView.findViewById(R.id.list_item_box);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.usernameEntry.setText(moodEventList.get(position).getUsername());
        holder.moodWordEntry.setText(
                String.format(
                        "%1$s"
                        , moodEventList
                                .get(position)
                                .getMood()
                                .getText()));
        holder.dateEntry.setText(
                String.format(
                        "On %1$s"
                        , moodEventList
                                .get(position)
                                .getDate()));
        holder.colorBox.setBackground(
                ContextCompat.getDrawable(context,
                        moodEventList.get(position)
                                .getMood()
                                .getShape()));
        /*holder.moodIcon.setBackground(
                ContextCompat.getDrawable(context
                        , moodEventList.get(position)
                                .getMood()
                                .getIcon()));*/
        holder.moodIcon.setImageResource(moodEventList.get(position)
                .getMood()
                .getIcon());
        return convertView;
    }

    static class ViewHolder {
        TextView usernameEntry;
        TextView moodWordEntry;
        TextView dateEntry;
        ImageView moodIcon;
        RelativeLayout colorBox;
    }
}