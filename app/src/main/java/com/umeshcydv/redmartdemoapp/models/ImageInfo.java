package com.umeshcydv.redmartdemoapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by umeshcydv on 12/11/17.
 */

public class ImageInfo {

    @SerializedName("h")
    private int height;

    @SerializedName("w")
    private int width;

    private String name;

    private int position;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
