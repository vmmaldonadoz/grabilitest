package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class ArtistAttributes {

    @SerializedName("href")
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
