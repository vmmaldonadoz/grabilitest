package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class ImName {

    @SerializedName("label")
    private String label;

    public ImName() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
