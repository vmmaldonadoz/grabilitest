package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class ImPrice {

    @SerializedName("label")
    private String label;

    @SerializedName("attributes")
    private PriceAttributes attributes;

    public ImPrice() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public PriceAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(PriceAttributes attributes) {
        this.attributes = attributes;
    }
}
