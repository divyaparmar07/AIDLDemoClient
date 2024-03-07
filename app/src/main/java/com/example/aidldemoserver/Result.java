package com.example.aidldemoserver;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Result implements Parcelable {
    private String fname;
    private String lname;
    private int rollno;
    private boolean pass;
    private float percentage;

    protected Result(Parcel in) {
        fname = in.readString();
        lname = in.readString();
        rollno = in.readInt();
        pass = in.readByte() != 0;
        percentage = in.readFloat();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public Result(String fname, String lname, int rollno, boolean pass, float percentage) {
        this.fname = fname;
        this.lname = lname;
        this.rollno = rollno;
        this.pass = pass;
        this.percentage = percentage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(fname);
        parcel.writeString(lname);
        parcel.writeInt(rollno);
        parcel.writeByte((byte) (pass ? 1 : 0));
        parcel.writeFloat(percentage);
    }
}