package com.example.android.sendmoods;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;

import java.util.Date;

public class MoodEvent implements Parcelable{
    private Date dateTime;
    private String reason;
    private String username;
    private Location address;
    private String emotion;
    private int popupShape;
    private String color;

    //what is this
    public MoodEvent(){}

    public MoodEvent(Date dateTime, String reason, String username, Location address, String emotion) {
        this.dateTime = dateTime;
        this.reason = reason;
        this.username = username;
        this.address = address;
        this.emotion = emotion;
    }

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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
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
    }

    @Override
    public int describeContents(){
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(popupShape);
        /*dest.write(this.message);
        dest.writeString(this.message);
        dest.writeString(this.message);
        dest.writeString(this.message);
        dest.writeString(this.message);
        dest.writeString(this.message);*/
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
