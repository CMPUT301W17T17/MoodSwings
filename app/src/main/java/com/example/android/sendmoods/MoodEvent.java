package com.example.android.sendmoods;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.android.sendmoods.Moods.Mood;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import static com.example.android.sendmoods.Constants.SAVEFILE_UID_FORMAT;
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
    private Boolean hasPhoto;
    private String photoFilename;
    private String photoPath;

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
        this.hasPhoto = false;
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

    public void updatePhoto(Context context, Bitmap photo){
        this.photo = photo;
        ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
        File directory = cw.getDir("bmp", EditMoodActivity.MODE_PRIVATE);
        File myFile = new File(directory, photoFilename);
        if (hasPhoto) {
            boolean deleted = myFile.delete();
        }
        photoFilename = SAVEFILE_UID_FORMAT.format(new Date());

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myFile);
            photo.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        photoPath = directory.getAbsolutePath();
    }

    public void loadPhoto(){
        if (hasPhoto){
            try {
                File f = new File(photoPath, photoFilename);
                photo = BitmapFactory.decodeStream(new FileInputStream(f));
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
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
        this.photo = in.readParcelable(Bitmap.class.getClassLoader());
        boolean[] boolArr = new boolean[1];
        in.readBooleanArray(boolArr);
        this.hasPhoto = boolArr[0];
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
        dest.writeBooleanArray(new boolean[] {hasPhoto});
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
