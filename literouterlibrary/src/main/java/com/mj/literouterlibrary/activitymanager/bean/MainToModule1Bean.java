package com.mj.literouterlibrary.activitymanager.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 2017/6/4.
 */

public class MainToModule1Bean  implements Parcelable {

    private  String id;
    private  String name;
    private  String age;

    public MainToModule1Bean() {
    }

    protected MainToModule1Bean(Parcel in) {
        id = in.readString();
        name = in.readString();
        age = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MainToModule1Bean> CREATOR = new Creator<MainToModule1Bean>() {
        @Override
        public MainToModule1Bean createFromParcel(Parcel in) {
            return new MainToModule1Bean(in);
        }

        @Override
        public MainToModule1Bean[] newArray(int size) {
            return new MainToModule1Bean[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MainToModule1Bean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
