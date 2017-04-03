package com.example.android.sendmoods;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonWriter;

import com.example.android.sendmoods.Moods.Mood;
import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Source;

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
    private String id;
    private Boolean hasId;

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
        this.hasId = false;
    }

    public void setId(String id) {
        this.id = id;
        this.hasId = true;
    }

    public String getId(){
        return id;
    }

    public Boolean hasId(){
        return hasId;
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
        this.hasId = in.readByte() != 0;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public String toString(){
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("Mood", mood.getText());
        /*json.put("username", username);
        json.put("date", date);
        json.put("time", time);
        json.put("address", address);
        json.put("reason", reason);
        json.put("longitude", longitude);
        json.put("photo", latitude);
        json.put("hasId", hasId);*/

        return (new Gson()).toJson(json);
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
        dest.writeByte((byte) (hasId ? 1 : 0));
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
