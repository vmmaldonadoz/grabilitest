package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class CategoryAttributes {

    @SerializedName("im:id")
    private String imId;
    @SerializedName("term")
    private String term;
    @SerializedName("scheme")
    private String scheme;
    @SerializedName("label")
    private String label;

    public CategoryAttributes() {
    }

    public String getImId() {
        return imId;
    }

    public void setImId(String imId) {
        this.imId = imId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
