package com.example.android.sendmoods;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Mood extends Activity implements Parcelable{
    private Date dateTime;
    private String reason;
    private String username;
    private Location address;
    private Emotion emotion;
    //image

    public Mood(Date dateTime, String reason, String username, Location address, Emotion emotion) {
        this.dateTime = dateTime;
        this.reason = reason;
        this.username = username;
        this.address = address;
        this.emotion = emotion;
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

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mood);
    }

    public Mood(Parcel in){
        this.message = in.readString();
    }

    @Override
    public int describeContents(){
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.write(this.message);
        dest.writeString(this.message);
        dest.writeString(this.message);
        dest.writeString(this.message);
        dest.writeString(this.message);
        dest.writeString(this.message);
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Mood createFromParcel(Parcel in) {
            return new Mood(in);
        }

        public Mood[] newArray(int size) {
            return new Mood[size];
        }
    };
}
