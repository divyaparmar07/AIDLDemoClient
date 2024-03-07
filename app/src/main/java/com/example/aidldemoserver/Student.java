package com.example.aidldemoserver;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Student implements Parcelable {
    private String fname;
    private String lname;
    private int rollno;
    private int phoneno;
    private String address;
    private List<Marks> marks;

    public Student(String fname, String lname, int rollno, int phoneno, String address, List<Marks> marks) {
        this.fname = fname;
        this.lname = lname;
        this.rollno = rollno;
        this.phoneno = phoneno;
        this.address = address;
        this.marks = marks;
    }

    protected Student(Parcel in) {
        fname = in.readString();
        lname = in.readString();
        rollno = in.readInt();
        phoneno = in.readInt();
        address = in.readString();
        marks = in.createTypedArrayList(Marks.CREATOR);
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
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

    public int getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(int phoneno) {
        this.phoneno = phoneno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Marks> getMarks() {
        return marks;
    }

    public void setMarks(List<Marks> marks) {
        this.marks = marks;
    }


    @NonNull
    @Override
    public String toString() {
        return "First Name : " + fname +
                ", Last Name : " + lname +
                ", RollNo : " + rollno +
                ", PhoneNo : " + phoneno +
                ", Address : " + address +
                ", Marks : " + marks;
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
        parcel.writeInt(phoneno);
        parcel.writeString(address);
        parcel.writeTypedList(marks);
    }
}