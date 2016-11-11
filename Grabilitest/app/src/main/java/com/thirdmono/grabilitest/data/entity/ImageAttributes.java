package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class ImageAttributes {

    @SerializedName("height")
    private String height;

    public ImageAttributes() {
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
