package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class ReleaseDateAttributes {

    @SerializedName("label")
    private String label;

    public ReleaseDateAttributes() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
