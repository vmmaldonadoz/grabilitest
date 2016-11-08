package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class Category {

    @SerializedName("attributes")
    private CategoryAttributes attributes;


    public CategoryAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(CategoryAttributes attributes) {
        this.attributes = attributes;
    }
}
