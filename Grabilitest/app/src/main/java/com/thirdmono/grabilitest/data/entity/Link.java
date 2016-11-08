package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class Link {

    @SerializedName("attributes")
    private LinkAttributes attributes;

    public LinkAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(LinkAttributes attributes) {
        this.attributes = attributes;
    }
}
