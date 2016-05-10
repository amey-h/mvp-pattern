package org.logitech.android.testapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by P7110298 on 5/6/2016.
 */
public class Devices implements Parcelable {

    private String deviceType;
    private String model;
    private String name;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static final Parcelable.Creator<Devices> CREATOR
            = new Parcelable.Creator<Devices>() {
        public Devices createFromParcel(Parcel in) {
            return new Devices(in);
        }

        public Devices[] newArray(int size) {
            return new Devices[size];
        }
    };

    public Devices(Parcel in) {

        this.deviceType = in.readString();
        this.model = in.readString();
        this.name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(deviceType);
        dest.writeString(model);
        dest.writeString(name);
    }
}
