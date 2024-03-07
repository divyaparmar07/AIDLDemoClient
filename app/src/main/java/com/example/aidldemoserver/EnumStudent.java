package com.example.aidldemoserver;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public enum EnumStudent implements Parcelable {
    PARTHIV(123),
    DIVYA(12345),
    RAVI(564);
    private final int id;

    EnumStudent(int id) {
        this.id = id;
    }

    EnumStudent(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<EnumStudent> CREATOR = new Creator<EnumStudent>() {
        @Override
        public EnumStudent createFromParcel(Parcel in) {
            return createENUM(in.readInt());
        }

        @Override
        public EnumStudent[] newArray(int size) {
            return new EnumStudent[size];
        }

        private EnumStudent createENUM(int id) {
            if (id == 12345) {
                return EnumStudent.DIVYA;
            } else if (id == 123) {
                return EnumStudent.PARTHIV;
            } else if (id == 564) {
                return EnumStudent.RAVI;
            }
            return null;
        }
    };

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
    }

}