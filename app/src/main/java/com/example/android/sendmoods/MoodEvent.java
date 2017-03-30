package com.example.android.sendmoods;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.sendmoods.Moods.Mood;

import java.util.Date;

import static com.example.android.sendmoods.Constants.SIMPLE_DATE_FORMAT;
import static com.example.android.sendmoods.Constants.SIMPLE_TIME_FORMAT;

public class MoodEvent implements Parcelable{

    private String date;
    private String time;
    private String reason;
    private String username;
    private String address;
    private double longitude;
    private double latitude;
    private Mood mood;
    private Bitmap photo;



    /**
     * Getters and setters for all the objects that make up the mood.
     */
    public MoodEvent(){
        this.mood = new Mood();
        this.date = SIMPLE_DATE_FORMAT.format((new Date()).getTime());
        this.time = SIMPLE_TIME_FORMAT.format((new Date()).getTime());
        this.reason = "";
        this.username = "SAMPLE_USERNAME";
        this.address = "SAMPLE_ADDRESS";
        this.latitude = 0.0;
        this.longitude = 0.0;

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

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Bitmap getPhoto() { return photo; }

    public void setPhoto(Bitmap photo) { this.photo = photo; }


    /*public Location getAddressGPS() {
        return addressGPS;
    }

    public void setAddressGPS(Location addressGPS) {
        this.addressGPS = addressGPS;
    }*/

    /**
     * @param in
     * The form in which the objects will be read by parcel, int or String.
     */
    public MoodEvent(Parcel in){
        this.mood = new Mood(in.readInt(), in.readInt(), in.readString(), in.readInt(), in.readInt());
        this.username = in.readString();
        this.date = in.readString();
        this.time = in.readString();
        this.address = in.readString();
        this.reason = in.readString();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.photo = in.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public int describeContents(){
        return 0;
    }

    /**
     * @param dest
     * @param flags
     * The parcel in which the objects will be written, int or String.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mood.getColor());
        dest.writeInt(mood.getShape());
        dest.writeString(mood.getText());
        dest.writeInt(mood.getIcon());
        dest.writeInt(mood.getIcon_bw());
        dest.writeString(username);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(address);
        dest.writeString(reason);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeParcelable(photo, 0);
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
