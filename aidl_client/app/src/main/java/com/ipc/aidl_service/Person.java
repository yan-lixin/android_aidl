package com.ipc.aidl_service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c), 2018-2019
 *
 * @author: lixin
 * Date: 2019/3/27
 * Description:
 */
public class Person implements Parcelable {
    private String name;
    private int psw;

    public Person(String name, int psw) {
        this.name = name;
        this.psw = psw;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.psw);
    }

    protected Person(Parcel in) {
        this.name = in.readString();
        this.psw = in.readInt();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", psw=" + psw +
                '}';
    }
}
