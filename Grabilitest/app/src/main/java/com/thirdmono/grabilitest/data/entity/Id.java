package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class Id {

    @SerializedName("label")
    private String label;

    @SerializedName("attributes")
    private IdAttributes attributes;

    public Id() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public IdAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(IdAttributes attributes) {
        this.attributes = attributes;
    }
}
