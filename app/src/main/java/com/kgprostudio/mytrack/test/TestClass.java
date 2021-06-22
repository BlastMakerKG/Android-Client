package com.kgprostudio.mytrack.test;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class TestClass implements Parcelable, Serializable {

    public TestClass(int age, String name) {
        this.age = age;
        this.name = name;
    }

    protected TestClass(Parcel in) {
        age = in.readInt();
        name = in.readString();
    }

    public static final Creator<TestClass> CREATOR = new Creator<TestClass>() {
        @Override
        public TestClass createFromParcel(Parcel in) {
            return new TestClass(in);
        }

        @Override
        public TestClass[] newArray(int size) {
            return new TestClass[size];
        }
    };

    @Override
    public String toString() {
        return "TestClass{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    private int age;
    private String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
    }
}
