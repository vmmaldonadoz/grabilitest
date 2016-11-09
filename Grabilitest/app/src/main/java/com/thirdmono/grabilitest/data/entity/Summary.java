package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class Summary {

    @SerializedName("label")
    private String label;

    public Summary() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
