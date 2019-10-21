package com.example.aidltest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 可以序列化的User
 * Created by 陈健宇 at 2019/10/14
 */
public class User implements Parcelable {

    private final String name;
    private final String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    //下面就是实现Parcelable接口需要实现的方法

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.password);
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

    protected User(Parcel in) {
        this.name = in.readString();
        this.password = in.readString();
    }

}
