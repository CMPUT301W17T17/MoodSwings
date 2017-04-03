package com.example.android.sendmoods;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import com.example.android.sendmoods.Moods.Mood;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import static com.example.android.sendmoods.Constants.SIMPLE_DATE_FORMAT;
import static com.example.android.sendmoods.Constants.SIMPLE_TIME_FORMAT;

/**
 * MoodEvent class to keep track of the MoodEvent objects.
 *
 * Primary objects user to identify and inidividual mood and its respective characteristics.
 */
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
    private Integer social;

    private String bitmapString;


    /**
     * initializing the objects of the mood that is created, to be stored.
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
        this.social=0;

    }

    /**
     * @return
     *
     * Getters and setters for the objects of the mood.
     */

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

    /**
     *
     * @return
     *
     * Get for String format of photo, which then calls String to Bitmap to convert it to Bitmap format.
     *
     * EditMoodActivity and MoodPopupActivity will need Bitmap format to display photo in the UI.
     */
    public Bitmap getPhoto() {
        return StringToBitMap(getBitmapString());

    }

    /**
     * @param photo
     *
     * Setter for Bitmap photo, which then calls BitMapToString to convert it to String format.
     */
    public void setPhoto(Bitmap photo) {
        setBitmapString(BitMapToString(this.photo = photo));

    }

    public Integer getSocial() {
        return social;
    }

    public void setSocial(Integer social) {
        this.social = social;
    }

    public String getBitmapString() {

        return bitmapString;
    }

    public void setBitmapString(String bitmapString) {
        this.bitmapString = bitmapString;
    }



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
        //this.photo = in.readParcelable(Bitmap.class.getClassLoader());
        this.social= in.readInt();
        this.bitmapString=in.readString();
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
        //dest.writeParcelable(photo, 0);
        dest.writeInt(social);
        dest.writeString(bitmapString);
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MoodEvent createFromParcel(Parcel in) {
            return new MoodEvent(in);
        }

        public MoodEvent[] newArray(int size) {
            return new MoodEvent[size];
        }
    };

    /**
     *
     * @param bitmap
     * @return a String value for the Bitmap photo.
     *
     * This is to aid in persistence.
     *
     * Source: http://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
     */
    public String BitMapToString(Bitmap bitmap){

        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    /**
     *
     * @param encodedString
     * @return a Bitmap value after reverting the photo format from a String.
     *
     * This is to aid in persistence.
     *
     * Source: http://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
     *
     */
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}

