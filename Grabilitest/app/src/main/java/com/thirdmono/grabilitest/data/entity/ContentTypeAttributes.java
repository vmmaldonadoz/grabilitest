package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class ContentTypeAttributes {

    @SerializedName("term")
    private String term;
    @SerializedName("label")
    private String label;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
