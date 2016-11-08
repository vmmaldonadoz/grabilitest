package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class ImContentType {

    @SerializedName("attributes")
    private ContentTypeAttributes attributes;

    public ContentTypeAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ContentTypeAttributes attributes) {
        this.attributes = attributes;
    }
}
