package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class ImReleaseDate {

    @SerializedName("label")
    private String label;

    @SerializedName("attributes")
    private ReleaseDateAttributes attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ReleaseDateAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ReleaseDateAttributes attributes) {
        this.attributes = attributes;
    }
}
