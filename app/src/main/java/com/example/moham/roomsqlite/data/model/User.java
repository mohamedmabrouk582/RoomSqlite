package com.example.moham.roomsqlite.data.model;

import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.moham.roomsqlite.data.db.DataConverter;

import java.util.List;

public class User implements Parcelable {
    private String userName;
    @TypeConverters(DataConverter.class)
    private List<String> types;

    public User(String userName, List<String> types) {
        this.userName = userName;
        this.types = types;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", types=" + types +
                '}';
    }

    public List<String> getTypes() {
        return types;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeStringList(this.types);
    }

    protected User(Parcel in) {
        this.userName = in.readString();
        this.types = in.createStringArrayList();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
