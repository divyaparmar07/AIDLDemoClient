package com.example.aidldemoserver;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Marks implements Parcelable {
    String subjectName;
    int mark;

    public Marks(String subjectName, int mark) {
        this.subjectName = subjectName;
        this.mark = mark;
    }

    protected Marks(Parcel in) {
        subjectName = in.readString();
        mark = in.readInt();
    }

    public static final Creator<Marks> CREATOR = new Creator<Marks>() {
        @Override
        public Marks createFromParcel(Parcel in) {
            return new Marks(in);
        }

        @Override
        public Marks[] newArray(int size) {
            return new Marks[size];
        }
    };

    public Marks() {

    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(subjectName);
        parcel.writeInt(mark);
    }
}