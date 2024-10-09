package com.example.lab5b;

import android.net.Uri;

public class Device {
    private String titleDevice;
    private String descDevice;
    private Uri imageDevice;
    private String type;

    public Device(String titleDevice, String descDevice, Uri imageDevice, String type) {
        this.titleDevice = titleDevice;
        this.descDevice = descDevice;
        this.imageDevice = imageDevice;
        this.type = type;
    }

    public String getTitleDevice() {
        return titleDevice;
    }

    public void setTitleDevice(String titleDevice) {
        this.titleDevice = titleDevice;
    }

    public String getDescDevice() {
        return descDevice;
    }

    public void setDescDevice(String descDevice) {
        this.descDevice = descDevice;
    }

    public Uri getImageDevice() {
        return imageDevice;
    }

    public void setImageDevice(Uri imageDevice) {
        this.imageDevice = imageDevice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
