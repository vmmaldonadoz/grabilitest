package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class Title {

    @SerializedName("label")
    private String label;

    public Title() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
