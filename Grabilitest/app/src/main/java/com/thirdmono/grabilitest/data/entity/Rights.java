package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class Rights {

    @SerializedName("label")
    private String label;

    public Rights() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
