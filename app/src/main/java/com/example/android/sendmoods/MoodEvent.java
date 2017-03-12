package com.example.android.sendmoods;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;

import java.util.Date;

public class MoodEvent implements Parcelable{

    private String date;
    private String time;
    private String reason;
    private String username;
    private Location addressGPS;
    private String address;
    private String emotion;
    private int popupShape;
    private String color;

    public MoodEvent(){}

    /*public MoodEvent(Date dateTime, String reason, String username, Location address, String emotion) {
        this.dateTime = dateTime;
        this.reason = reason;
        this.username = username;
        this.address = address;
        this.emotion = emotion;
    }*/

    public Drawable getListBox(Context context){
        return ContextCompat.getDrawable(context, R.drawable.list_box_pink);
    }

    public int getPopupShape(){
        return popupShape;
    }

    public void setPopupShape(int popupShape){
        this.popupShape = popupShape;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getColor(){
        return color;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public MoodEvent(Parcel in){
        this.popupShape = in.readInt();
        this.username = in.readString();
        this.emotion = in.readString();
        this.date = in.readString();
        this.time = in.readString();
        this.address = in.readString();
        this.reason = in.readString();
    }

    @Override
    public int describeContents(){
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(popupShape);
        dest.writeString(username);
        dest.writeString(emotion);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(address);
        dest.writeString(reason);
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MoodEvent createFromParcel(Parcel in) {
            return new MoodEvent(in);
        }

        public MoodEvent[] newArray(int size) {
            return new MoodEvent[size];
        }
    };
}
