package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class IdAttributes {

    @SerializedName("im:id")
    private String imId;
    @SerializedName("im:bundleId")
    private String imBundleId;

    public IdAttributes() {
    }

    public String getImId() {
        return imId;
    }

    public void setImId(String imId) {
        this.imId = imId;
    }

    public String getImBundleId() {
        return imBundleId;
    }

    public void setImBundleId(String imBundleId) {
        this.imBundleId = imBundleId;
    }
}
