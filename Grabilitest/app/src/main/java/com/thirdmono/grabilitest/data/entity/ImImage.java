package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class ImImage {

    @SerializedName("label")
    private String label;

    @SerializedName("attributes")
    private ImageAttributes imageAttributes;

    public ImImage() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ImageAttributes getImageAttributes() {
        return imageAttributes;
    }

    public void setImageAttributes(ImageAttributes imageAttributes) {
        this.imageAttributes = imageAttributes;
    }
}
